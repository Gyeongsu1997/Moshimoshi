package com.moshimoshi.message.service;

import com.moshimoshi.message.dto.MessageRequest;
import com.moshimoshi.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional
    public void sendMessage(MessageRequest messageRequest) {
//        getWriter(messageRequest);
//        Message2 message1 = null;
//        Message2 message2 = null;
//
//        messageRepository.save(message1);
//        messageRepository.save(message2);
    }
}
