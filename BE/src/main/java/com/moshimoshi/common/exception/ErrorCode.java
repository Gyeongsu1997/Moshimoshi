package com.moshimoshi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 공통 에러
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, 100, "Resource Not Found"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 110, "Method Not Allowed"),

    /**
     * 인증 및 인가 관련 에러
     */
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 200, "Token Expired"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 210, "Invalid Token"),
    TOKEN_NOT_EXIST(HttpStatus.UNAUTHORIZED, 220, "Token Not Exist"),
    FORBIDDEN(HttpStatus.FORBIDDEN, 230, "Permission Denied"),

    /**
     * 사용자 관련 에러
     */
    DUPLICATE_ID(HttpStatus.BAD_REQUEST, 300, "ID Already Exist"),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, 310, "User Not Exist"),
    INCORRECT_PASSWORD(HttpStatus.BAD_REQUEST, 320, "Incorrect Password");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
