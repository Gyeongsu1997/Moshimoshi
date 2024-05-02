package com.moshimoshi.message.domain;

import com.moshimoshi.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Message extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    private String content;
}
