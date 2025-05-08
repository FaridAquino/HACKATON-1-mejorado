package com.example.hackaton_1_mejorada.AI;

import com.example.hackaton_1_mejorada.Domain.DTO.requestSolicitudDTO;
import com.example.hackaton_1_mejorada.Domain.solicitud.Solicitud;
import com.example.hackaton_1_mejorada.AI.ChatOpenAI.OpenAIChatService;
import com.example.hackaton_1_mejorada.AI.DeepSeek.DeepSeekChatService;
import com.example.hackaton_1_mejorada.AI.Llama.LlamaChatService;
import com.example.hackaton_1_mejorada.Domain.solicitud.SolicitudService;
import com.example.hackaton_1_mejorada.Domain.solicitud.solicitudModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final OpenAIChatService openAIChatService;
    private final DeepSeekChatService deepSeekChatService;
    private final LlamaChatService llamaChatService;
    private final SolicitudService solicitudService;
    private final AIService aiService;

    @Autowired
    public AIController(OpenAIChatService openAIChatService,
                        DeepSeekChatService deepSeekChatService,
                        LlamaChatService llamaChatService,
                        SolicitudService solicitudService, AIService aiService) { // Inyectar SolicitudService
        this.openAIChatService = openAIChatService;
        this.deepSeekChatService = deepSeekChatService;
        this.llamaChatService = llamaChatService;
        this.solicitudService = solicitudService; // Asignar el servicio
        this.aiService = aiService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/chat")
    public Solicitud promptChat(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {

        if (solicitudDTO.getModelo_usado()== solicitudModelo.DEEPSEEK){
            return deepSeekChatService.chat(solicitudDTO, id);
        }

        if (solicitudDTO.getModelo_usado()== solicitudModelo.GPT){
            return llamaChatService.chat(solicitudDTO,id);
        }

        return openAIChatService.chat(solicitudDTO, id);

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
    public ResponseEntity<List<String>> getAvailableModels(@RequestParam Long id) {

        List<String> modelos=aiService.obtenerModelos(id);

        return new ResponseEntity<>(modelos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/history")
    public List<Solicitud> getUserHistory(@RequestParam Long userId) {
        return solicitudService.findByUserId(userId);
    }
}
