package com.moshimoshi.thread.domain;

import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadTest {
    @DisplayName("스레드 생성")
    @Test
    void createThread() {
        //given
        User user = new User();
        ThreadPostRequest request = threadPostRequest();

        //when
        Thread thread = Thread.createThread(user, request);

        //then
        assertEquals(request.getContent(), thread.getContent());
        assertEquals(request.isAnonymous(), thread.isAnonymous());
        assertEquals(0, thread.getLikeCount());
        assertEquals(0, thread.getCommentSequence());
        assertEquals(0, thread.getNumberOfActiveComments());
        assertFalse(thread.isDeleted());
        assertEquals(user, thread.getUser());
        assertTrue(user.getThreads().contains(thread));
        assertEquals(0, thread.getComments().size());
    }

    private ThreadPostRequest threadPostRequest() {
        return ThreadPostRequest.builder()
                .content("hello")
                .anonymous(true)
                .build();
    }
}