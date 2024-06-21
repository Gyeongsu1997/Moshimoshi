package com.moshimoshi.comment.controller;

import com.moshimoshi.auth.resolver.Login;
import com.moshimoshi.comment.dto.CommentRequest;
import com.moshimoshi.comment.dto.CommentResponse;
import com.moshimoshi.comment.service.CommentService;
import com.moshimoshi.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/threads/{threadId}")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentResponse> getCommentList(@PathVariable Long threadId) {
        return commentService.list(threadId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    /**
     * Handlers below here require user to be logged in
     */

    @PostMapping("/comments")
    public ResponseEntity<?> comment(@Login User user, @PathVariable Long threadId, @RequestBody CommentRequest commentRequest) throws URISyntaxException {
        commentService.comment(user, threadId, commentRequest);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads/" + threadId + "/comments"))
                .build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@Login User user, @PathVariable Long threadId, @PathVariable Long commentId) throws URISyntaxException {
        commentService.deleteOne(user, commentId);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads/" + threadId + "/comments"))
                .build();
    }

    @PostMapping("/comments/{commentId}/replies")
    public ResponseEntity<?> reply(@Login User user, @PathVariable Long threadId, @PathVariable Long commentId,  @RequestBody CommentRequest commentRequest) throws URISyntaxException {
        commentService.reply(user, threadId, commentId, commentRequest);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads/" + threadId + "/comments"))
                .build();
    }
}
