package com.moshimoshi.comment.dto;

import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.common.Define;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {
    private Long threadId;
    private Long commentId;
    private String writer;
    private String content;
    private LocalDateTime createdAt;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .threadId(comment.getThread().getId())
                .commentId(comment.getId())
                .writer(Define.ANONYM)
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
