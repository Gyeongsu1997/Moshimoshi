package com.moshimoshi.message.controller;

import com.moshimoshi.message.dto.MessageRequest;
import com.moshimoshi.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public String send(@RequestBody MessageRequest messageRequest) {
        return "abdc";
    }
}
