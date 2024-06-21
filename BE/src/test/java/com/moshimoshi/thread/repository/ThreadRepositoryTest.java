package com.moshimoshi.thread.repository;

import com.moshimoshi.JpaAuditingConfig;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(JpaAuditingConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ThreadRepositoryTest {
    @Autowired
    ThreadRepository threadRepository;

    @DisplayName("스레드 저장")
    @Test
    void save() {
        //given
        User writer = new User();
        ThreadPostRequest request = threadPostRequest();
        Thread thread = Thread.createThread(writer, request);

        //when
        Thread savedThread = threadRepository.save(thread);

        //then
        assertEquals(thread.getContent(), savedThread.getContent());
        assertEquals(thread.isAnonymous(), savedThread.isAnonymous());
        assertFalse(savedThread.isDeleted());
        assertEquals(0, savedThread.getLikeCount());
        assertEquals(0, savedThread.getCommentSequence());
        assertEquals(0, savedThread.getNumberOfActiveComments());
        assertEquals(writer, savedThread.getWriter());
        assertTrue(writer.getThreads().contains(savedThread));
    }

    private ThreadPostRequest threadPostRequest() {
        return ThreadPostRequest.builder()
                .content("안녕하세요")
                .anonymous(true)
                .build();
    }
}