package com.moshimoshi.report.domain;

import com.moshimoshi.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Report extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;
}
