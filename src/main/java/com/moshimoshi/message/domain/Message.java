package com.moshimoshi.message.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Message {
    @Id
    @Column(name = "message_id")
    private Long id;
}
