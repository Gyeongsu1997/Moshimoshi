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
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않는 요청 메서드입니다.", "요청 메서드를 확인해 주세요."),
    DUPLICATE_USERID(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다.", "다른 아이디를 입력해주세요."),
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
