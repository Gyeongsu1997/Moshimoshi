package com.moshimoshi.report.repository;

import com.moshimoshi.report.domain.Report;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportRepository {
    private final EntityManager em;

    public void save(Report report) {
        em.persist(report);
    }

    public Optional<Report> findById(Long id) {
        Report report = em.find(Report.class, id);
        return Optional.ofNullable(report);
    }

    public void deleteById(Long id) {

    }
}
