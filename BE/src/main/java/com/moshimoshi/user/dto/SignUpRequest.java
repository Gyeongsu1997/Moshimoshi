package com.moshimoshi.user.dto;

import com.moshimoshi.user.domain.User;
import lombok.Getter;

@Getter
public class SignUpRequest {
    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private String profile;

    public User toEntity() {
        return User.createUser(this);
    }
}
