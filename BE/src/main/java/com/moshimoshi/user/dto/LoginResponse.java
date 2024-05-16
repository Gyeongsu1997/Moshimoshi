package com.moshimoshi.user.dto;

import com.moshimoshi.user.domain.Role;
import com.moshimoshi.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String loginId;
    private Role role;

    public static LoginResponse from(User user) {
        return new LoginResponse(user.getLoginId(), user.getRole());
    }
}
