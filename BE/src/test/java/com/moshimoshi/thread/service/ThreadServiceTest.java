package com.moshimoshi.thread.service;

import com.moshimoshi.thread.repository.ThreadRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ThreadServiceTest {
    @InjectMocks
    ThreadService threadService;

    @Mock
    ThreadRepository threadRepository;

    @DisplayName("스레드 목록 조회")
    @Test
    void list() {
        
    }
}
