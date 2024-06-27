package com.moshimoshi.thread.service;

import com.moshimoshi.common.exception.BusinessException;
import com.moshimoshi.common.exception.ErrorCode;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import com.moshimoshi.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.*;

@ExtendWith(MockitoExtension.class)
class ThreadServiceTest {
    @InjectMocks
    ThreadService threadService;

    @Mock
    ThreadRepository threadRepository;

    @DisplayName("스레드 조회 성공")
    @Test
    void findThreadSuccess() {
        //given
        Long threadId = 1L;
        Thread thread = new Thread();
        setField(thread, "id", threadId);

        when(threadRepository.findByIdAndDeletedFalse(threadId))
                .thenReturn(Optional.of(thread));

        //when
        Thread foundThread = threadService.findThread(threadId);

        //then
        assertEquals(threadId, foundThread.getId());
    }

    @DisplayName("스레드 조회 실패: Not Found")
    @Test
    void findThreadNotFound() {
        //given
        Long threadId = 1L;

        when(threadRepository.findByIdAndDeletedFalse(threadId))
                .thenReturn(Optional.empty());

        //when
        BusinessException exception = assertThrows(BusinessException.class, () -> threadService.findThread(threadId));

        //then
        assertEquals(ErrorCode.NOT_FOUND.getHttpStatus(), exception.getHttpStatus());
        assertEquals(ErrorCode.NOT_FOUND.getCode(), exception.getCode());
        assertEquals(ErrorCode.NOT_FOUND.getMessage(), exception.getMessage());
    }
}
