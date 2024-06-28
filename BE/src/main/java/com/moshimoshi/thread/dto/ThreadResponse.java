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
    private int likeCount;
    private Long userId;
    private int countOfComments;
    private LocalDateTime createdAt;

    public static ThreadResponse from(Thread thread) {
        return ThreadResponse.builder()
                .threadId(thread.getId())
                .content(thread.getContent())
                .anonymous(thread.isAnonymous())
                .likeCount(thread.getLikeCount())
                .userId(thread.getUser().getId())
                .countOfComments(thread.getCountOfActiveComments())
                .createdAt(thread.getCreatedAt())
                .build();
    }
}
