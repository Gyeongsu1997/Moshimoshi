package com.moshimoshi.comment.dto;

import com.moshimoshi.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {
    private Long commentId;
    private String content;
    private int commentNumber;
    private boolean anonymous;
    private Long userId;
    private Long threadId;
    private Long parentId;
    private LocalDateTime createdAt;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .commentNumber(comment.getCommentNumber())
                .anonymous(comment.isAnonymous())
                .userId(comment.getWriter().getId())
                .threadId(comment.getThread().getId())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : 0)
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
