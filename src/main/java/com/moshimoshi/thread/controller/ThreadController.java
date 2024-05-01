package com.moshimoshi.thread.controller;

import com.moshimoshi.thread.dto.PostRequest;
import com.moshimoshi.thread.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/threads")
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
    public String read(@PathVariable("threadId") Long threadId) {
        return "abc";
    }

    @DeleteMapping("/{threadId}")
    public String delete(@PathVariable("threadId") Long threadId) {
        return "abc";
    }
}
