package com.moshimoshi.report.service;

import com.moshimoshi.exception.CommonException;
import com.moshimoshi.exception.ErrorCode;
import com.moshimoshi.report.domain.Report;
import com.moshimoshi.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public void save() {
//        reportRepository.save();
    }

    public void findOne(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new CommonException(ErrorCode.REPORT_NOT_EXIST));
    }

    public void deleteOne(Long reportId) {

    }
}
