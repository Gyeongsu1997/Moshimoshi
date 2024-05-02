package com.moshimoshi.comment.controller;

import com.moshimoshi.comment.service.CommentService;
import com.moshimoshi.thread.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/threads")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{threadId}/comments")
    public String list() {
        return "abc";
    }

    @PostMapping("/{threadId}/comments")
    public String post(@RequestBody PostRequest postRequest) {
        return "abdc";
    }

    @DeleteMapping("/{threadId}/comments/{commentId}")
    public String delete(@PathVariable("threadId") Long threadId) {
        return "abc";
    }

    //todo 대댓글 기능
}
