package com.example.hackaton_1_mejorada.AI.DeepSeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat/deep")
public class DeepSeekChatController {

    private final DeepSeekChatService chatService;

    @Autowired
    public DeepSeekChatController(DeepSeekChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String prompt(@RequestBody String prompt) {
        return chatService.chat(prompt);
    }
}