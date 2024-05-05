package com.moshimoshi.report.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportReason {
    PORNOGRAPHY("음란물");

    private final String phrase;
}