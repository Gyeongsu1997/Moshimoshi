package com.moshimoshi.user.repository;

import com.moshimoshi.user.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository {
    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByLoginId(String loginId) {
        return em.createQuery("select u from User u where u.loginId = :loginId", User.class)
                .setParameter("loginId", loginId)
                .getResultList()
                .stream()
                .findAny();
    }

    public Optional<User> findByRefreshToken(String refreshToken) {
        return em.createQuery("select u from User u where u.authentication.refreshToken = :refreshToken", User.class)
                .setParameter("refreshToken", refreshToken)
                .getResultList()
                .stream()
                .findAny();
    }
}
