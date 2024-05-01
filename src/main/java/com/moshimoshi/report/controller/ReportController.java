package com.moshimoshi.report.controller;

import com.moshimoshi.report.dto.ReportRequest;
import com.moshimoshi.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public String send(@RequestBody ReportRequest reportRequest) {
        return "abdc";
    }

    /**
     * below here, only admin can call
     */
    @GetMapping
    public String list() {
        return "abc";
    }

    @GetMapping("/{reportId}")
    public String show(@PathVariable("reportId") Long reportId) {
        return "abc";
    }

}
