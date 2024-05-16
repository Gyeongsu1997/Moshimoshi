package com.moshimoshi.thread.dto;

import lombok.Getter;

@Getter
public class ThreadPostRequest {
    private String content;
    private boolean anonymous;
}
