package com.moshimoshi.user.dto;

import com.moshimoshi.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpRequest {
    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private String avatar;

    public User toEntity() {
        return User.createUser(this);
    }
}
