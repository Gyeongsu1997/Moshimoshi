package com.moshimoshi.thread.domain;

import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.common.domain.BaseEntity;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Thread extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_id")
    private Long id;

    private String content;
    private boolean anonymous;
    private int likeCount = 0;
    private int commentSequence = 0;
    private int numberOfActiveComments = 0; //삭제되지 않은 댓글의 수
    private boolean deleted = false;
    private LocalDateTime deletedAt;
    private String deletedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public static Thread createThread(User user, ThreadPostRequest threadPostRequest) {
        Thread thread = new Thread();
        thread.content = threadPostRequest.getContent();
        thread.anonymous = threadPostRequest.isAnonymous();
        thread.user = user;
        user.getThreads().add(thread);
        return thread;
    }

    public List<Comment> getActiveComments() {
        return this.comments.stream().filter(c -> !c.isDeleted()).toList();
    }

    public synchronized Comment addComment(User user, CommentRequest commentRequest) {
        this.commentSequence++; //concurrency issue
        Comment comment = Comment.createComment(user, commentRequest, this);
        this.comments.add(comment);
        numberOfActiveComments++;
        return comment;
    }

    public synchronized int like() {
        return ++this.likeCount; //concurrency issue
    }
}
