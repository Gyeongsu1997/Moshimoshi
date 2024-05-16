package com.moshimoshi.thread.domain;

import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.common.domain.BaseTimeEntity;
import com.moshimoshi.thread.dto.PostRequest;
import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Thread extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_id")
    private Long id;

    private String content;
    private int thumbsUp;
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User writer;

    @OneToMany(mappedBy = "thread")
    private List<Comment> comments = new ArrayList<>();

    public static Thread of(PostRequest postRequest, User writer) {
        Thread thread = new Thread();
        thread.content = postRequest.getContent();
        thread.thumbsUp = 0;
        thread.deleted = false;
        thread.writer = writer;
        writer.getThreads().add(thread);
        return thread;
    }

    public void deleteThread() {
        deleted = true;
    }

    public boolean isWriter(User user) {
        return user != null && user.getId().equals(this.writer.getId());
    }
}
