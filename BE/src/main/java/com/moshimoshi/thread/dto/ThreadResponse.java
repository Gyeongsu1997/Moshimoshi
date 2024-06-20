package com.moshimoshi.thread.dto;

import com.moshimoshi.thread.domain.Thread;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ThreadResponse {
    private Long threadId;
    private String content;
    private boolean anonymous;
    private int thumbsUp;
    private Long userId;
    private int numberOfComments;
    private LocalDateTime createdAt;

    public static ThreadResponse from(Thread thread) {
        return ThreadResponse.builder()
                .threadId(thread.getId())
                .content(thread.getContent())
                .anonymous(thread.isAnonymous())
                .thumbsUp(thread.getThumbsUp())
                .userId(thread.getWriter().getId())
                .numberOfComments(thread.getNumberOfActiveComments())
                .createdAt(thread.getCreatedAt())
                .build();
    }
}
