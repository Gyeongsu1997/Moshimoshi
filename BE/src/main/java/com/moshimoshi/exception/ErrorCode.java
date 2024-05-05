package com.moshimoshi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 인증 및 인가 관련 에러
     */
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Access Token이 만료되었습니다.","Access Token을 재발급하기 위해 Refresh Token이 필요합니다."),
    /**
     * 스레드 관련 에러
     */
    THREAD_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당 스레드가 존재하지 않습니다.",""),
    /**
     * 리포트(신고) 관련 에러
     */
    REPORT_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당 리포트가 존재하지 않습니다.","");

    private final HttpStatus httpStatus;
    private final String message;
    private final String solution;
}
