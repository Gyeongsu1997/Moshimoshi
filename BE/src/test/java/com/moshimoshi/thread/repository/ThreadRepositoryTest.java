package com.moshimoshi.thread.repository;

import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.user.domain.Role;
import com.moshimoshi.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ThreadRepositoryTest {
    @Autowired
    ThreadRepository threadRepository;

    @DisplayName("스레드 저장")
    @Test
    void save() {
        //given
        User writer = user();
        ThreadPostRequest request = threadPostRequest();
        Thread thread = thread(writer, request);

        //when
        Thread savedThread = threadRepository.save(thread);

        //then
        assertEquals(thread.getContent(), savedThread.getContent());
        assertEquals(thread.isAnonymous(), savedThread.isAnonymous());
        assertEquals(0, savedThread.getThumbsUp());
        assertFalse(savedThread.isDeleted());
        assertEquals(0, savedThread.getCommentSequence());
        assertEquals(0, savedThread.getNumberOfActiveComments());
        assertEquals(writer, savedThread.getWriter());
        assertTrue(writer.getThreads().contains(savedThread));
    }

    private Thread thread(User writer, ThreadPostRequest request) {
        return Thread.of(writer, request);
    }

    private User user() {
        return User.builder()
                .loginId("test")
                .password("1234")
                .nickname("test")
                .email("test@test.com")
                .avatar("test")
                .role(Role.USER)
                .build();
    }

    private ThreadPostRequest threadPostRequest() {
        return ThreadPostRequest.builder()
                .content("안녕하세요")
                .anonymous(true)
                .build();
    }
}