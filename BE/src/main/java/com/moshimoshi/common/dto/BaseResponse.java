package com.moshimoshi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private String message;

    private T data;

    private BaseResponse(String message) {
        this.message = message;
    }
    public static BaseResponse<?> from(String message) {
        return new BaseResponse<>(message);
    }

    public static <T> BaseResponse<T> of(String message, T data) {
        return new BaseResponse<>(message, data);
    }
}
