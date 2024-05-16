package com.moshimoshi.auth.dto;

import com.moshimoshi.user.domain.Role;
import com.moshimoshi.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogInResponse {
    private String loginId;
    private Role role;

    public static LogInResponse from(User user) {
        return new LogInResponse(user.getLoginId(), user.getRole());
    }
}
