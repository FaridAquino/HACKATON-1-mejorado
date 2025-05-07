package com.example.hackaton_1_mejorada.AI.Llama;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class LlamaChatController {

    private final LlamaChatService chatService;

    @Autowired
    public LlamaChatController(LlamaChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String prompt(@RequestBody String prompt) {
        return chatService.chat(prompt);
    }
}