package com.moshimoshi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 인증 및 인가 관련 에러
     */
    ACCESS_TOKEN_NOT_EXIST(HttpStatus.BAD_REQUEST, "Access Token이 존재하지 않습니다.", "다시 로그인 해주세요."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Access Token이 만료되었습니다.","Access Token을 재발급하기 위해 Refresh Token이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없는 사용자입니다.", ""),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당 유저가 존재하지 않습니다.",""),
    PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다.","비밀번호를 다시 입력해 주세요."),
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
