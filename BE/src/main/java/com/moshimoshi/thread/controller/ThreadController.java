package com.moshimoshi.thread.controller;

import com.moshimoshi.auth.resolver.Login;
import com.moshimoshi.common.dto.BaseResponse;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.ThreadPostRequest;
import com.moshimoshi.thread.dto.ThreadListResponse;
import com.moshimoshi.thread.dto.ThreadResponse;
import com.moshimoshi.thread.service.ThreadService;
import com.moshimoshi.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/threads")
public class ThreadController {
    private final ThreadService threadService;

    @GetMapping
    public BaseResponse<ThreadListResponse> getThreadList(@RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        return BaseResponse.of(ThreadListResponse.of(threadService.findThreadList(pageNumber)));
    }

    @GetMapping("/{threadId}")
    public BaseResponse<ThreadResponse> getThread(@PathVariable Long threadId) {
        Thread thread = threadService.findThread(threadId);
        return BaseResponse.of(ThreadResponse.from(thread));
    }

    /**
     * Handlers below here require user to be logged in
     */

    @PostMapping
    public BaseResponse<?> postThread(@Login User user, @RequestBody ThreadPostRequest threadPostRequest) {
        threadService.createThread(user, threadPostRequest);
        return BaseResponse.success();
    }

    @DeleteMapping("/{threadId}")
    public ResponseEntity<?> deleteThread(@Login User user, @PathVariable Long threadId) throws URISyntaxException {
        threadService.deleteThread(user, threadId);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads"))
                .build();
    }

    @PostMapping("/{threadId}/like")
    public ResponseEntity<?> likeThread(@Login User user, @PathVariable Long threadId) throws URISyntaxException {
        threadService.likeThread(threadId);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads/" + threadId))
                .build();
    }
}
