package com.moshimoshi.thread.dto;

import lombok.Getter;

@Getter
public class PostRequest {
    private String content;
    private boolean anonymous;
}
