package com.example.family.domain.report.repository;

import com.example.family.domain.report.entity.Report;
import com.example.family.domain.resident.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Report.ReportPk> {
    Optional<Report> findByResidentAndBirthReportQualificationsCodeNotNull(Resident resident);
    Optional<Report> findByResidentAndDeathReportQualificationsCodeNotNull(Resident resident);

    default Report getBirthReport(Resident resident) {
        return findByResidentAndBirthReportQualificationsCodeNotNull(resident)
                .orElseThrow(() -> new IllegalArgumentException("출생신고서가 존재하지 않습니다."));
    }

    default Report getDeathReport(Resident resident) {
        return findByResidentAndDeathReportQualificationsCodeNotNull(resident)
                .orElseThrow(() -> new IllegalArgumentException("사망신고서가 존재하지 않습니다."));
    }
}
