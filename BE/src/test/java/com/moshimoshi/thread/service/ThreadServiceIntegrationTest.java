package com.moshimoshi.thread.service;

import com.moshimoshi.common.exception.BusinessException;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.repository.ThreadRepository;
import com.moshimoshi.user.domain.User;
import com.moshimoshi.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.test.util.ReflectionTestUtils.setField;

@SpringBootTest
class ThreadServiceIntegrationTest {
    @Autowired
    ThreadService threadService;
    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void deleteThread() throws Exception {
        //given
        User user = userRepository.saveAndFlush(new User());

        Thread thread = new Thread();
        setField(thread, "user", user);
        Long threadId = threadRepository.saveAndFlush(thread).getId();

        int count = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(count);

        AtomicInteger fail = new AtomicInteger();

        //when
        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                try {
                    threadService.deleteThread(user, threadId);
                } catch (BusinessException e) {
                    fail.getAndIncrement();
                } finally {
                    latch.countDown();
                }
            });

        }

        latch.await();

        //then
        Assertions.assertEquals(99, fail.get());

        threadRepository.deleteAll();
        userRepository.deleteAll();
    }
}
