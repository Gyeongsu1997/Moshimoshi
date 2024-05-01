package com.moshimoshi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Access Token이 만료되었습니다.","Access Token이 만료되었으므로 재발급받아야 합니다." );

    private final HttpStatus httpStatus;
    private final String message;
    private final String solution;

}
