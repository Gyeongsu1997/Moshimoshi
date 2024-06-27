package com.moshimoshi.thread.repository;

import com.moshimoshi.JpaAuditingConfig;
import com.moshimoshi.thread.domain.Thread;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.ReflectionTestUtils.*;

@DataJpaTest
@Import(JpaAuditingConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ThreadRepositoryTest {
    @Autowired
    ThreadRepository threadRepository;

    @DisplayName("삭제된 스레드는 조회할 수 없다")
    @Test
    void findByIdAndDeletedFalse() {
        //given
        Thread thread = new Thread();
        setField(thread, "deleted", true);
        Long threadId = threadRepository.saveAndFlush(thread).getId();

        //when
        Optional<Thread> optional = threadRepository.findByIdAndDeletedFalse(threadId);

        //then
        assertTrue(optional.isEmpty());
    }

    @DisplayName("스레드를 삭제하면 deleted, deletedAt, deletedBy 컬럼이 업데이트된다.")
    @Test
    void updateDeletedById() {
        //given
        Long id = threadRepository.saveAndFlush(new Thread()).getId();
        String deletedBy = "user";

        //when
        threadRepository.updateDeletedById(id, LocalDateTime.now(), deletedBy);

        //then
        Thread thread = threadRepository.findById(id).orElseThrow();
        assertTrue(thread.isDeleted());
        assertNotNull(thread.getDeletedAt());
        assertEquals(deletedBy, thread.getDeletedBy());
    }
}