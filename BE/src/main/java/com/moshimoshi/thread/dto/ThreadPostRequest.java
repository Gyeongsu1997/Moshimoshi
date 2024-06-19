package com.moshimoshi.thread.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ThreadPostRequest {
    private String content;
    private boolean anonymous;
}
