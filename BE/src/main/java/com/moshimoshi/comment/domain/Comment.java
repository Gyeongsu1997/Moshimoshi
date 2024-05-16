package com.moshimoshi.comment.domain;

import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.common.domain.BaseTimeEntity;
import com.moshimoshi.thread.domain.Thread;
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
public class Comment extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private int commentNumber;
    private boolean anonymous;
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    private Thread thread;

    /**
     * 댓글 - 대댓글 연관관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    public static Comment of(User writer, CommentRequest commentRequest, Thread thread) {
        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .commentNumber(thread.getCommentSequence())
                .anonymous(commentRequest.isAnonymous())
                .deleted(false)
                .writer(writer)
                .thread(thread)
                .build();
        writer.getComments().add(comment);
        return comment;
    }

    public void deleteComment() {
        deleted = true;
    }

    public boolean isWriter(User user) {
        return user != null && user.getId().equals(this.writer.getId());
    }

    public void addReply(Comment reply) {
        this.child.add(reply);
        reply.parent = this;
    }
}
