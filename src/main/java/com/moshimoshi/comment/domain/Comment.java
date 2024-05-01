package com.moshimoshi.comment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Comment {
    @Id
    @Column(name = "comment_id")
    private Long id;
}
