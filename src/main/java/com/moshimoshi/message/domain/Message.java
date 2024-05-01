package com.moshimoshi.message.domain;

import com.moshimoshi.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Message extends BaseTimeEntity {
    @Id
    @Column(name = "message_id")
    private Long id;
}
