package com.example.family.repository;

import com.example.family.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Report.ReportPk> {
}
