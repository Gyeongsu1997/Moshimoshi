package com.moshimoshi.thread.domain;

import com.moshimoshi.bookmark.domain.Bookmark;
import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.common.domain.BaseEntity;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

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
    private boolean deleted = false;
    private int thumbsUp = 0;
    private int commentSequence = 0;
    private int numberOfActiveComments = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User writer;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public static Thread createThread(User writer, ThreadPostRequest threadPostRequest) {
        Thread thread = new Thread();
        thread.content = threadPostRequest.getContent();
        thread.anonymous = threadPostRequest.isAnonymous();
        thread.writer = writer;
        writer.getThreads().add(thread);
        return thread;
    }

    public void deleteThread() {
        this.deleted = true;
        delete("user");
    }

    public boolean isWriter(User user) {
        return user != null && user.getId().equals(this.writer.getId());
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

    public synchronized int thumbsUp() {
        return ++this.thumbsUp; //concurrency issue
    }
}
