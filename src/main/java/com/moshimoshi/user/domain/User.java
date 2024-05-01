package com.moshimoshi.user.domain;

import com.moshimoshi.auth.domain.Authentication;
import com.moshimoshi.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String userId;
    private String password;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;
}
