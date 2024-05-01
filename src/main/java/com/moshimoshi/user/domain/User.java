package com.moshimoshi.user.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @Column(name = "users_id")
    private Long id;

    private String userId;
    private String password;
    private String email;
}
