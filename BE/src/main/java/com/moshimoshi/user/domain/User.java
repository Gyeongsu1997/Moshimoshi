package com.moshimoshi.user.domain;

import com.moshimoshi.auth.domain.Authentication;
import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.common.domain.BaseTimeEntity;
import com.moshimoshi.message.domain.UserMessage;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.auth.dto.LogInRequest;
import com.moshimoshi.user.dto.SignUpRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;

    @OneToMany(mappedBy = "writer")
    private List<Thread> threads = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();

    //todo 북마크한 스레드 리스트

    @OneToMany(mappedBy = "user")
    private List<UserMessage> userMessages = new ArrayList<>();

    public static User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.loginId = signUpRequest.getLoginId();
        user.password = signUpRequest.getPassword();
        user.nickname = signUpRequest.getNickname();
        user.email = signUpRequest.getEmail();
        user.avatar = signUpRequest.getAvatar();
        user.role = Role.USER;
        user.authentication = Authentication.of(user);
        return user;
    }

    public boolean isCorrectPassword(LogInRequest loginRequest) {
        return this.loginId.equals(loginRequest.getLoginId()) && this.password.equals(loginRequest.getPassword());
    }

    public void updateRefreshToken(String refreshToken) {
        this.authentication.updateRefreshToken(refreshToken);
    }
}
