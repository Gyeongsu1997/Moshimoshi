package com.moshimoshi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private static final int success = 0;
    private static final String successMsg = "OK";
    private int code;
    private String message;
    private T data;

    private BaseResponse(String message) {
        this.message = message;
    }
    public static BaseResponse<?> from(String message) {
        return new BaseResponse<>(message);
    }

    public static <T> BaseResponse<T> of(T data) {
        return new BaseResponse<>(success, successMsg, data);
    }

    public static <T> BaseResponse<T> of(String message, T data) {
        return new BaseResponse<>(success, message, data);
    }

    public static <T> BaseResponse<T> of(int code, String message, T data) {
        return new BaseResponse<>(code, message, data);
    }
}
