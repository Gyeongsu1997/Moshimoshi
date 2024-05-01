package com.moshimoshi.thread.domain;

import com.moshimoshi.comment.domain.Comment;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Thread {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_id")
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "thread")
    List<Comment> comments = new ArrayList<>();
}
