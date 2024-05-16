package com.moshimoshi.auth.domain;

import com.moshimoshi.user.domain.Role;
import com.moshimoshi.user.domain.User;
import com.moshimoshi.auth.dto.LogInResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {
    private String loginId;
    private Role role;

    public static AuthenticatedUser from(LogInResponse loginResponse) {
        return new AuthenticatedUser(loginResponse.getLoginId(), loginResponse.getRole());
    }

    public static AuthenticatedUser of(User user) {
        return new AuthenticatedUser(user.getLoginId(), user.getRole());
    }
}
