package com.example.hackaton_1_mejorada.AI;

import com.example.hackaton_1_mejorada.Domain.DTO.requestSolicitudDTO;
import com.example.hackaton_1_mejorada.Domain.solicitud.Solicitud;
import com.example.hackaton_1_mejorada.AI.ChatOpenAI.OpenAIChatService;
import com.example.hackaton_1_mejorada.AI.DeepSeek.DeepSeekChatService;
import com.example.hackaton_1_mejorada.AI.Llama.LlamaChatService;
import com.example.hackaton_1_mejorada.Domain.solicitud.SolicitudService; // Incluir SolicitudService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final OpenAIChatService openAIChatService;
    private final DeepSeekChatService deepSeekChatService;
    private final LlamaChatService llamaChatService;
    private final SolicitudService solicitudService; // Inyectar SolicitudService

    @Autowired
    public AIController(OpenAIChatService openAIChatService,
                        DeepSeekChatService deepSeekChatService,
                        LlamaChatService llamaChatService,
                        SolicitudService solicitudService) { // Inyectar SolicitudService
        this.openAIChatService = openAIChatService;
        this.deepSeekChatService = deepSeekChatService;
        this.llamaChatService = llamaChatService;
        this.solicitudService = solicitudService; // Asignar el servicio
    }

    // Endpoint para consultas de chat (GPT)
    @PostMapping("/chat")
    public Solicitud promptChat(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {
        return openAIChatService.chat(solicitudDTO, id);
    }

    // Endpoint para solicitudes de completado de texto (DeepSeek)
    @PostMapping("/completion")
    public Solicitud promptCompletion(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {
        return deepSeekChatService.chat(solicitudDTO, id);
    }

    // Endpoint para consultas multimodales (Llama)
    @PostMapping("/multimodal")
    public Solicitud promptMultimodal(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {
        return llamaChatService.chat(solicitudDTO, id);
    }

    // Endpoint para obtener la lista de modelos disponibles para el usuario
    @GetMapping("/models")
    public String getAvailableModels() {
        return "gpt, deepseek, llama"; // Aquí puedes devolver la lista de modelos disponibles dinámicamente si lo deseas
    }

    // Endpoint para obtener el historial de solicitudes del usuario
    @GetMapping("/history")
    public List<Solicitud> getUserHistory(@RequestParam Long userId) {
        // Usar el servicio para acceder al historial
        return solicitudService.findByUserId(userId); // Utilizamos el servicio para acceder al historial
    }
}
