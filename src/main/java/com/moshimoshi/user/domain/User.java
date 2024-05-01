package com.moshimoshi.user.domain;

import com.moshimoshi.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "users_id")
    private Long id;

    private String userId;
    private String password;
    private String email;
}
