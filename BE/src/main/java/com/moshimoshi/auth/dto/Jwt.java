package com.moshimoshi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Jwt {
    private String accessToken;
    private String refreshToken;

    public static Jwt of(String accessToken, String refreshToken) {
        return new Jwt(accessToken, refreshToken);
    }
}
