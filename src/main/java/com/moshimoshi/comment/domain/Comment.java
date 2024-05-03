package com.moshimoshi.comment.domain;

import com.moshimoshi.common.domain.BaseTimeEntity;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;
    private String deleted; // Y or N

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

    public void addReply(Comment reply) {
        this.child.add(reply);
        reply.parent = this;
    }

    public void deleteComment() {
        deleted = "Y";
    }
}
