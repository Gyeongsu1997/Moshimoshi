package com.moshimoshi.thread.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ThreadResponse {
    private String writer;
    private String content;
    private LocalDateTime createdAt;
}
