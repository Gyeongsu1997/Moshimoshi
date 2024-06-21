package com.moshimoshi.thread.repository;

import com.moshimoshi.thread.domain.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Page<Thread> findByDeleted(boolean deleted, Pageable pageable);
    Optional<Thread> findByIdAndDeletedFalse(Long threadId);
}
