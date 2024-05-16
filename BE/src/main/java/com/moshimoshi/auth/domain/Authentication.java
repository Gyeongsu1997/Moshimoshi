package com.moshimoshi.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Authentication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authentication_id")
    private Long id;

    private String refreshToken;

//    @OneToOne(mappedBy = "authentication", fetch = FetchType.LAZY)
//    private User user;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
