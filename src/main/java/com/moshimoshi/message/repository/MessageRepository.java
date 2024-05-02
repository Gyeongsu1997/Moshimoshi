package com.moshimoshi.message.repository;

import com.moshimoshi.message.domain.Message;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MessageRepository {
    private final EntityManager em;

    public void save(Message message) {
        em.persist(message);
    }

    public void deleteById(Long id) {

    }
}
