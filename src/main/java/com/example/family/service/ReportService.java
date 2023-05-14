package com.example.family.service;

import com.example.family.domain.BirthEditForm;
import com.example.family.domain.BirthReportForm;
import com.example.family.domain.DeathEditForm;
import com.example.family.domain.DeathReportForm;
import com.example.family.entity.Report;
import com.example.family.entity.Resident;
import com.example.family.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository repository;

    public void insertBirthReport(Resident reportResident, Resident targetResident, BirthReportForm form) {
        Report report = Report.builder()
                .pk(new Report.ReportPk(
                        Report.TypeCode.출생, targetResident.getSerialNumber(), reportResident.getSerialNumber()))
                .resident(targetResident)
                .reportResident(reportResident)
                .reportDate(form.getReportDate())
                .birthReportQualificationsCode(form.getBirthReportQualificationsCode())
                .emailAddress(form.getEmail())
                .phoneNumber(form.getPhoneNumber())
                .build();

        repository.save(report);
    }

    public Report getReport(Report.TypeCode typeCode, int targetResidentSerialNumber, int reportResidentSerialNumber) {
        return repository.findById(new Report.ReportPk(
                typeCode, targetResidentSerialNumber, reportResidentSerialNumber))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 관계가 없습니다."));
    }


    public void modifyBirthReport(Resident reportResident, Resident targetResident, BirthEditForm form) {
        Report report =
                getReport(Report.TypeCode.출생, targetResident.getSerialNumber(), reportResident.getSerialNumber());
        updateBirthReport(report, form);
        repository.save(report);
    }

    private void updateBirthReport(Report report, BirthEditForm form) {
        report.setReportDate(form.getReportDate());
        report.setBirthReportQualificationsCode(form.getBirthReportQualificationsCode());
        report.setEmailAddress(form.getEmail());
        report.setPhoneNumber(form.getPhoneNumber());
    }

    public void deleteBirthReport(int serialNumber, int targetSerialNumber) {
        Report report = getReport(Report.TypeCode.출생, targetSerialNumber, serialNumber);
        repository.delete(report);
    }

    public void insertDeathReport(Resident reportResident, Resident targetResident, DeathReportForm form) {
        Report report = Report.builder()
                .pk(new Report.ReportPk(
                        Report.TypeCode.사망, targetResident.getSerialNumber(), reportResident.getSerialNumber()))
                .resident(targetResident)
                .reportResident(reportResident)
                .reportDate(form.getReportDate())
                .deathReportQualificationsCode(form.getDeathReportQualificationsCode())
                .emailAddress(form.getEmail())
                .phoneNumber(form.getPhoneNumber())
                .build();

        repository.save(report);
    }

    public void modifyDeathReport(Resident reportResident, Resident targetResident, DeathEditForm form) {
        Report report =
                getReport(Report.TypeCode.사망, targetResident.getSerialNumber(), reportResident.getSerialNumber());
        updateDeathReport(report, form);
        repository.save(report);
    }

    private void updateDeathReport(Report report, DeathEditForm form) {
        report.setReportDate(form.getReportDate());
        report.setDeathReportQualificationsCode(form.getDeathReportQualificationsCode());
        report.setEmailAddress(form.getEmail());
        report.setPhoneNumber(form.getPhoneNumber());
    }

    public void deleteDeathReport(int serialNumber, int targetSerialNumber) {
        Report report = getReport(Report.TypeCode.사망, targetSerialNumber, serialNumber);
        repository.delete(report);
    }
}
