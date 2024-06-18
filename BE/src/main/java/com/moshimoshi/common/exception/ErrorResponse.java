package com.moshimoshi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;

    public static ErrorResponse from(BusinessException e) {
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }
}
