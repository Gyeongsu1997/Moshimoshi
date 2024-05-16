package com.moshimoshi.thread.repository;

import com.moshimoshi.thread.domain.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Page<Thread> findByDeleted(boolean deleted, Pageable pageable);
}
