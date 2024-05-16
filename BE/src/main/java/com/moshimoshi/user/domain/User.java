package com.moshimoshi.user.domain;

import com.moshimoshi.auth.domain.Authentication;
import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.common.domain.BaseTimeEntity;
import com.moshimoshi.message.domain.UserMessage;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.user.dto.LoginRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String loginId;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;

    @OneToMany(mappedBy = "writer")
    private List<Thread> threads = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();

    //todo 북마크한 스레드 리스트

    @OneToMany(mappedBy = "user")
    private List<UserMessage> userMessages = new ArrayList<>();

    public boolean isCorrectPassword(LoginRequest loginRequest) {
        return this.loginId.equals(loginRequest.getLoginId()) && this.password.equals(loginRequest.getPassword());
    }

    public void updateRefreshToken(String refreshToken) {
        this.authentication.updateRefreshToken(refreshToken);
    }
}
