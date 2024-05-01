package com.moshimoshi.thread.service;

import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThreadService {
    private final ThreadRepository threadRepository;

    public void post() {
        threadRepository.save();
    }

    public void findOne(Long threadId) {
        Optional<Thread> optional = threadRepository.findById(threadId);
    }
}
