package com.moshimoshi.thread.domain;

import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.common.domain.BaseTimeEntity;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Thread extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_id")
    private Long id;

    private String content;
    private int thumbsUp;
    private boolean anonymous;
    private boolean deleted;
    private int commentSequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User writer;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public static Thread of(User writer, ThreadPostRequest threadPostRequest) {
        Thread thread = Thread.builder()
                .content(threadPostRequest.getContent())
                .thumbsUp(0)
                .anonymous(threadPostRequest.isAnonymous())
                .deleted(false)
                .commentSequence(0)
                .writer(writer)
                .build();
        writer.getThreads().add(thread);
        return thread;
    }

    public void deleteThread() {
        deleted = true;
    }

    public boolean isWriter(User user) {
        return user != null && user.getId().equals(this.writer.getId());
    }

    public List<Comment> getAvailableComments() {
        return this.comments.stream().filter(c -> !c.isDeleted()).toList();
    }

    public int getAvailableNumberOfComments() {
        return getAvailableComments().size();
    }

    public synchronized Comment addComment(User user, CommentRequest commentRequest) {
        this.commentSequence++; //동시성 이슈 발생 가능
        Comment comment = Comment.of(user, commentRequest, this);
        this.comments.add(comment);
        return comment;
    }

    public synchronized int thumbsUp() {
        return ++this.thumbsUp; //동시성 이슈 발생 가능
    }
}
