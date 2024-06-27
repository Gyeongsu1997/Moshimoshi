package com.moshimoshi.comment.domain;

import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.common.domain.BaseEntity;
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
@Getter
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private boolean anonymous;
    private int commentOrder;
    private boolean deleted = false;

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

    public static Comment createComment(User writer, CommentRequest commentRequest, Thread thread) {
        Comment comment = new Comment();
        comment.content = commentRequest.getContent();
        comment.anonymous = commentRequest.isAnonymous();;
        comment.commentOrder = thread.getCommentSequence();
        comment.writer = writer;
        comment.thread = thread;
        writer.getComments().add(comment);
        return comment;
    }

    public void deleteComment() {
        this.deleted = true;
//        delete("user");
        // thread의 numberOfActiveComment--;
    }

    public boolean isWriter(User user) {
        return user != null && user.getId().equals(this.writer.getId());
    }

    public void addReply(Comment reply) {
        this.child.add(reply);
        reply.parent = this;
    }
}
