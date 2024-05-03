package com.moshimoshi.admin.service;

import com.moshimoshi.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {
    private final ReportRepository reportRepository;

    public void reportList() {
    }
}
