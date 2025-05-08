package com.example.hackaton_1_mejorada.AI.Llama;

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

import java.util.Arrays;
import java.util.List;

@Service
public class LlamaChatService {

    private final ChatCompletionsClient client;

    private final UsuarioRepository usuarioRepository;

    private final SolicitudRepository solicitudRepository;


    public LlamaChatService(UsuarioRepository usuarioRepository, SolicitudRepository solicitudRepository) {
        this.usuarioRepository = usuarioRepository;
        this.solicitudRepository = solicitudRepository;

        String key = System.getenv("OPENKEY");
        String endpoint = "https://models.github.ai/inference";

        this.client = new ChatCompletionsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }

    public Solicitud chat(requestSolicitudDTO solicitudDTO, Long id) {
        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        Solicitud solicitud=new Solicitud();

        //Se agrega la solicitud
        solicitud.setConsulta(solicitudDTO.getConsulta());
        solicitud.setConsultante(usuario);

        solicitudRepository.save(solicitud);

        List<Limites> limites=usuario.getLimites();

        for (Limites limite : limites) {
            if (limite.getModelo()== LimitesModelo.LLAMA && limite.getTokenSobrantes()==0){
                solicitud.setRespuesta_estado(solicitudEstado.RECHAZADA);
                solicitud.setRespuesta("No puedes acceder al modelo de LLAMA porque no tienes tokens");
                return solicitudRepository.save(solicitud);
            }

            if (limite.getModelo() == LimitesModelo.LLAMA){
                limite.setTokenSobrantes(limite.getTokenSobrantes()-solicitud.getTokens_necesarios());
            }
        }

        usuarioRepository.save(usuario);


        List<ChatRequestMessage> messages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful assistant."),
                new ChatRequestUserMessage(solicitud.getConsulta())
        );

        ChatCompletionsOptions options = new ChatCompletionsOptions(messages);
        String model = "meta/Llama-4-Scout-17B-16E-Instruct";
        options.setModel(model);

        ChatCompletions completions = client.complete(options);
        if (completions.getChoices() != null && !completions.getChoices().isEmpty()) {
            solicitud.setRespuesta_estado(solicitudEstado.APROBADA);
            solicitud.setRespuesta(completions.getChoices().get(0).getMessage().getContent());

            return solicitudRepository.save(solicitud);
        } else {
            solicitud.setRespuesta_estado(solicitudEstado.RECHAZADA);
            solicitud.setRespuesta("Error, no se pudo responder la consulta con LLAMA");
            return solicitudRepository.save(solicitud);
        }
    }
}