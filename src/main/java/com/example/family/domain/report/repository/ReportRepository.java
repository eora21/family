package com.example.family.domain.report.repository;

import com.example.family.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Report.ReportPk> {
}
