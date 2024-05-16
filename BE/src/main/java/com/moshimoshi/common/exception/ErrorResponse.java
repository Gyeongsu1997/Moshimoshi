package com.moshimoshi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String solution;

    public static ErrorResponse from(CommonException e) {
        return new ErrorResponse(e.getMessage(), e.getSolution());
    }
}
