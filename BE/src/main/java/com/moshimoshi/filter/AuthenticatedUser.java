package com.moshimoshi.filter;

import com.moshimoshi.user.domain.Role;
import com.moshimoshi.user.dto.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedUser {
    private String loginId;
    private Role role;

    public static AuthenticatedUser from(LoginResponse loginResponse) {
        return new AuthenticatedUser(loginResponse.getLoginId(), loginResponse.getRole());
    }
}
