package com.example.hackaton_1_mejorada.AI.Llama;

import com.example.hackaton_1_mejorada.Domain.DTO.requestSolicitudDTO;
import com.example.hackaton_1_mejorada.Domain.solicitud.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/multimodal")
public class LlamaChatController {

    private final LlamaChatService chatService;

    @Autowired
    public LlamaChatController(LlamaChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Solicitud prompt(@RequestBody requestSolicitudDTO solicitudDTO, @RequestParam Long id) {
        return chatService.chat(solicitudDTO, id);
    }
}
