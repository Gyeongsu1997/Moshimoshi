package com.moshimoshi.report.controller;

import com.moshimoshi.report.dto.ReportRequest;
import com.moshimoshi.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public String send(@RequestBody ReportRequest reportRequest) {
        return "abdc";
    }
}
