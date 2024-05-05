package com.moshimoshi.comment.controller;

import com.moshimoshi.comment.domain.Comment;
import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.comment.dto.CommentResponse;
import com.moshimoshi.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/threads")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{threadId}/comments")
    public List<CommentResponse> list(@PathVariable("threadId") Long threadId) {
        return commentService.list(threadId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    @PostMapping("/{threadId}/comments")
    public String post(@RequestBody CommentRequest commentRequest) {
        return "abdc";
    }

    @DeleteMapping("/{threadId}/comments/{commentId}")
    public String delete(@PathVariable("threadId") Long threadId) {
        return "abc";
    }

    //대댓글
    @PostMapping("/{threadId}/comments/{commentId}/replies")
    public String reply() {
        return "abc";
    }
}
