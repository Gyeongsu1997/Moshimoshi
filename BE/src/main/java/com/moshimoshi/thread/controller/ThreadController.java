package com.moshimoshi.thread.controller;

import com.moshimoshi.thread.domain.Thread;
import com.moshimoshi.thread.dto.PostRequest;
import com.moshimoshi.thread.dto.ThreadListResponse;
import com.moshimoshi.thread.dto.ThreadResponse;
import com.moshimoshi.thread.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @PostMapping
    public String post(@RequestBody PostRequest postRequest) {
        return "abdc";
    }

    @GetMapping("/{threadId}")
    public ThreadResponse show(@PathVariable Long threadId) {
        Thread thread = threadService.findOne(threadId);
        return ThreadResponse.from(thread);
    }

    @DeleteMapping("/{threadId}")
    public ResponseEntity<?> delete(@PathVariable Long threadId) throws URISyntaxException {
        threadService.deleteOne(threadId);
        return ResponseEntity.status(HttpStatus.SEE_OTHER) //303 GET으로 Redirect
                .location(new URI("/api/threads"))
                .build();
    }
}
