package com.moshimoshi.thread.repository;

import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Page<Thread> findThreadsByDeletedFalse(Pageable pageable);
    Optional<Thread> findByIdAndDeletedFalse(Long id);
    Optional<Thread> findByIdAndUserAndDeletedFalse(Long id, User user);

    @Modifying(clearAutomatically = true)
    @Query("update Thread t " +
            "set t.deleted = true, t.deletedAt = :deletedAt, t.deletedBy = :deletedBy " +
            "where t.id = :id and t.deleted = false")
    int updateDeletedById(Long id, LocalDateTime deletedAt, String deletedBy);

    @Modifying(clearAutomatically = true)
    @Query("update Thread t " +
            "set t.likeCount = t.likeCount + 1" +
            "where t.id = :id and t.deleted = false")
    void updateLikeCountById(Long id);

}
