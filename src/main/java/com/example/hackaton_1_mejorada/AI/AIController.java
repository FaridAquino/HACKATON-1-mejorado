package com.example.hackaton_1_mejorada.AI;

import com.example.hackaton_1_mejorada.Domain.DTO.requestSolicitudDTO;
import com.example.hackaton_1_mejorada.Domain.solicitud.Solicitud;
import com.example.hackaton_1_mejorada.AI.ChatOpenAI.OpenAIChatService;
import com.example.hackaton_1_mejorada.AI.DeepSeek.DeepSeekChatService;
import com.example.hackaton_1_mejorada.AI.Llama.LlamaChatService;
import com.example.hackaton_1_mejorada.Domain.solicitud.SolicitudService; // Incluir SolicitudService
import com.example.hackaton_1_mejorada.Domain.solicitud.solicitudModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    public Solicitud promptChat(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {

        if (solicitudDTO.getModelo_usado()== solicitudModelo.DEEPSEEK){
            return deepSeekChatService.chat(solicitudDTO, id);
        }

        if (solicitudDTO.getModelo_usado()== solicitudModelo.GPT){
            return openAIChatService.chat(solicitudDTO, id);
        }

        return llamaChatService.chat(solicitudDTO, id);

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/completion")
    public Solicitud promptCompletion(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {
        if (solicitudDTO.getModelo_usado()== solicitudModelo.DEEPSEEK){
            return deepSeekChatService.chat(solicitudDTO, id);
        }

        if (solicitudDTO.getModelo_usado()== solicitudModelo.GPT){
            return openAIChatService.chat(solicitudDTO, id);
        }

        return llamaChatService.chat(solicitudDTO, id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/multimodal")
    public Solicitud promptMultimodal(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {
        if (solicitudDTO.getModelo_usado()== solicitudModelo.DEEPSEEK){
            return deepSeekChatService.chat(solicitudDTO, id);
        }

        if (solicitudDTO.getModelo_usado()== solicitudModelo.GPT){
            return openAIChatService.chat(solicitudDTO, id);
        }

        return llamaChatService.chat(solicitudDTO, id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/models")
    public String getAvailableModels(@RequestParam Long id) {


        return "gpt, deepseek, llama";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/history")
    public List<Solicitud> getUserHistory(@RequestParam Long userId) {
        return solicitudService.findByUserId(userId); // Utilizamos el servicio para acceder al historial
    }
}
