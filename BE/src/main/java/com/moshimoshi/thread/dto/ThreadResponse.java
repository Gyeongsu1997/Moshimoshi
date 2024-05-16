package com.moshimoshi.thread.dto;

import com.moshimoshi.common.Define;
import com.moshimoshi.thread.domain.Thread;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ThreadResponse {
    private Long threadId;
    private String writer;
    private String content;
    private int thumbsUp;
    private LocalDateTime createdAt;

    public static ThreadResponse from(Thread thread) {
        return ThreadResponse.builder()
                .threadId(thread.getId())
                .writer(Define.ANONYM)
                .content(thread.getContent())
                .thumbsUp(thread.getThumbsUp())
                .createdAt(thread.getCreatedAt())
                .build();
    }
}
