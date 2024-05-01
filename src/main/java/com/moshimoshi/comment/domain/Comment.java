package com.moshimoshi.comment.domain;

import com.moshimoshi.common.BaseTimeEntity;
import com.moshimoshi.thread.domain.Thread;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseTimeEntity {
    @Id
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id")
    private Thread thread;
}
