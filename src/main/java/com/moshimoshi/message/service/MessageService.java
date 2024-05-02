package com.moshimoshi.message.service;

import com.moshimoshi.message.domain.Message;
import com.moshimoshi.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void sendMessage() {
        Message message1 = null;
        Message message2 = null;

        messageRepository.save(message1);
        messageRepository.save(message2);
    }
}
