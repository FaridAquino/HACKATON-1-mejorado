package com.example.hackaton_1_mejorada.AI.ChatOpenAI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class OpenAIChatController {

    private final OpenAIChatService chatService;

    @Autowired
    public OpenAIChatController(OpenAIChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String prompt(@RequestBody String prompt) {
        return chatService.chat(prompt);
    }
}