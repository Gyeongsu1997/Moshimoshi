package com.moshimoshi.thread.repository;

import com.moshimoshi.thread.domain.Thread;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ThreadRepository {
    private final EntityManager em;

    public void save(Thread thread) {
        em.persist(thread);
    }

    public Optional<Thread> findById(Long id) {
        Thread thread = em.find(Thread.class, id);
        return Optional.ofNullable(thread);
    }
}
