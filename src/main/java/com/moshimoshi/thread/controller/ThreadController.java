package com.moshimoshi.thread.controller;

import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.PostRequest;
import com.moshimoshi.thread.dto.ThreadResponse;
import com.moshimoshi.thread.service.ThreadService;
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
    public String list() {
        return "abc";
    }

    @PostMapping
    public String post(@RequestBody PostRequest postRequest) {
        return "abdc";
    }

    @GetMapping("/{threadId}")
    public ThreadResponse show(@PathVariable("threadId") Long threadId) {
        Thread thread = threadService.findOne(threadId);
        return ThreadResponse.from(thread);
    }

    @DeleteMapping("/{threadId}")
    public ResponseEntity<?> delete(@PathVariable("threadId") Long threadId) throws URISyntaxException {
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads"))
                .build();
    }
}
