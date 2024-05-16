package com.moshimoshi.thread.controller;

import com.moshimoshi.auth.resolver.Login;
import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.PostRequest;
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
    public ThreadListResponse list(@RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        return ThreadListResponse.of(threadService.list(pageNumber));
    }

    @GetMapping("/{threadId}")
    public ThreadResponse show(@PathVariable Long threadId) {
        Thread thread = threadService.findOne(threadId);
        return ThreadResponse.from(thread);
    }

    /**
     * Handler which is below here requires user to be logged in
     */

    @PostMapping
    public ResponseEntity<?> post(@Login User user, @RequestBody PostRequest postRequest) throws URISyntaxException {
        threadService.post(user, postRequest);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads"))
                .build();
    }

    @DeleteMapping("/{threadId}")
    public ResponseEntity<?> delete(@Login User user, @PathVariable Long threadId) throws URISyntaxException {
        threadService.deleteOne(user, threadId);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads"))
                .build();
    }
}
