package com.moshimoshi.admin.controller;

import com.moshimoshi.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/api/reports")
    public String list() {
        return "abc";
    }

    @GetMapping("/api/reports/{reportId}")
    public String show(@PathVariable("reportId") Long reportId) {
        return "abc";
    }
}
