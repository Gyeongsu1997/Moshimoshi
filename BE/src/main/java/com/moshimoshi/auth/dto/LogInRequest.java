package com.moshimoshi.auth.dto;

import lombok.Getter;

@Getter
public class LogInRequest {
    private String loginId;
    private String password;
}

