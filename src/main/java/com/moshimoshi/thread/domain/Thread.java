package com.moshimoshi.thread.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Thread {
    @Id
    @Column(name = "thread_id")
    private Long id;
}
