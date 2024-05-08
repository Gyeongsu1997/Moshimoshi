package com.moshimoshi.thread.repository;

import com.moshimoshi.thread.domain.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
