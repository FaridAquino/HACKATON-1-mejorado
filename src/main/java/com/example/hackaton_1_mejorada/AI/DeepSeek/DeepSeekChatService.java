package com.example.hackaton_1_mejorada.AI.DeepSeek;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.example.hackaton_1_mejorada.Domain.DTO.requestSolicitudDTO;
import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.limites.LimitesModelo;
import com.example.hackaton_1_mejorada.Domain.solicitud.Solicitud;
import com.example.hackaton_1_mejorada.Domain.solicitud.SolicitudRepository;
import com.example.hackaton_1_mejorada.Domain.solicitud.solicitudEstado;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DeepSeekChatService {

    private final ChatCompletionsClient client;
    private final UsuarioRepository usuarioRepository;
    private final SolicitudRepository solicitudRepository;

    public DeepSeekChatService(UsuarioRepository usuarioRepository, SolicitudRepository solicitudRepository) {
        this.usuarioRepository = usuarioRepository;
        this.solicitudRepository = solicitudRepository;
        String key = System.getenv("OPENKEY");
        String endpoint = "https://models.github.ai/inference";

        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }

    @Transactional
    public Solicitud chat(requestSolicitudDTO solicitudDTO, Long id) {
        // Obtener usuario desde repositorio
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Construir solicitud sin persistir aún
        Solicitud solicitud = new Solicitud();
        solicitud.setNombrePeticion(solicitudDTO.getNombrePeticion());
        solicitud.setConsulta(solicitudDTO.getConsulta());
        solicitud.setConsultante(usuario);

        // Validar y actualizar límites antes del completado
        for (Limites limite : usuario.getLimites()) {
            if (limite.getModelo() == LimitesModelo.DEEPSEEK) {
                if (limite.getTokenSobrantes() <= 0) {
                    solicitud.setRespuesta_estado(solicitudEstado.RECHAZADA);
                    solicitud.setRespuesta("No puedes acceder al modelo de DeepSeek porque no tienes tokens");
                    return solicitudRepository.save(solicitud);
                }
                limite.setTokenSobrantes(limite.getTokenSobrantes() - solicitud.getTokens_necesarios());
            }
        }

        // Instrucción de sistema para filtrar solo completados de texto
        List<ChatRequestMessage> messages = Arrays.asList(
                new ChatRequestSystemMessage(
                        "Eres un servicio de completado de texto. Únicamente debes aceptar solicitudes que impliquen continuar o finalizar un texto dado. " +
                                "Si la petición del usuario NO es un completado de texto, responde exactamente: 'Error: solo se aceptan solicitudes de completado de texto'."
                ),
                new ChatRequestUserMessage(solicitud.getConsulta())
        );

        // Llamada al modelo DeepSeek
        ChatCompletionsOptions options = new ChatCompletionsOptions(messages);
        options.setModel("deepseek/DeepSeek-V3-0324");
        ChatCompletions completions = client.complete(options);

        // Procesar respuesta
        if (completions.getChoices() != null && !completions.getChoices().isEmpty()) {
            solicitud.setRespuesta_estado(solicitudEstado.APROBADA);
            solicitud.setRespuesta(completions.getChoices().get(0).getMessage().getContent());
        } else {
            solicitud.setRespuesta_estado(solicitudEstado.RECHAZADA);
            solicitud.setRespuesta("Error, no se pudo responder la consulta con DeepSeek");
        }

        // Persistir solicitud con todos los datos finales
        return solicitudRepository.save(solicitud);
    }
}
