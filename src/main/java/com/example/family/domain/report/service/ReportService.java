package com.example.family.domain.report.service;

import com.example.family.domain.resident.service.ResidentService;
import com.example.family.domain.report.model.form.BirthEditForm;
import com.example.family.domain.report.model.form.BirthReportForm;
import com.example.family.domain.report.model.form.DeathEditForm;
import com.example.family.domain.report.model.form.DeathReportForm;
import com.example.family.domain.report.entity.Report;
import com.example.family.domain.resident.entity.Resident;
import com.example.family.domain.report.repository.ReportRepository;
import com.example.family.domain.resident.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ResidentService residentService;
    private final ReportRepository reportRepository;
    private final ResidentRepository residentRepository;

    @Transactional
    public void insertBirthReport(Resident reportResident, BirthReportForm form) {
        Resident targetResident = residentService.saveResident(form.getResidentForm());

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

        reportRepository.save(report);
    }

    public Report getReport(Report.TypeCode typeCode, int targetResidentSerialNumber, int reportResidentSerialNumber) {
        return reportRepository.findById(new Report.ReportPk(
                typeCode, targetResidentSerialNumber, reportResidentSerialNumber))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 관계가 없습니다."));
    }


    public void modifyBirthReport(Resident reportResident, Resident targetResident, BirthEditForm form) {
        Report report =
                getReport(Report.TypeCode.출생, targetResident.getSerialNumber(), reportResident.getSerialNumber());
        updateBirthReport(report, form);
        reportRepository.save(report);
    }

    private void updateBirthReport(Report report, BirthEditForm form) {
        report.setReportDate(form.getReportDate());
        report.setBirthReportQualificationsCode(form.getBirthReportQualificationsCode());
        report.setEmailAddress(form.getEmail());
        report.setPhoneNumber(form.getPhoneNumber());
    }

    public void deleteBirthReport(int serialNumber, int targetSerialNumber) {
        Report report = getReport(Report.TypeCode.출생, targetSerialNumber, serialNumber);
        reportRepository.delete(report);

        residentRepository.deleteById(targetSerialNumber);
    }

    @Transactional
    public void insertDeathReport(Resident reportResident, Resident targetResident, DeathReportForm form) {
        targetResident.setDeathDate(form.getDeathDate());
        targetResident.setDeathPlaceCode(form.getDeathPlaceCode());
        targetResident.setDeathPlaceAddress(form.getDeathPlaceAddress());

        residentRepository.save(targetResident);

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

        reportRepository.save(report);
    }

    public void modifyDeathReport(Resident reportResident, Resident targetResident, DeathEditForm form) {
        targetResident.setDeathDate(form.getDeathDate());
        targetResident.setDeathPlaceCode(form.getDeathPlaceCode());
        targetResident.setDeathPlaceAddress(form.getDeathPlaceAddress());

        residentRepository.save(targetResident);

        Report report =
                getReport(Report.TypeCode.사망, targetResident.getSerialNumber(), reportResident.getSerialNumber());
        updateDeathReport(report, form);
        reportRepository.save(report);
    }

    private void updateDeathReport(Report report, DeathEditForm form) {
        report.setReportDate(form.getReportDate());
        report.setDeathReportQualificationsCode(form.getDeathReportQualificationsCode());
        report.setEmailAddress(form.getEmail());
        report.setPhoneNumber(form.getPhoneNumber());
    }

    public void deleteDeathReport(int serialNumber, Resident targetResident) {
        targetResident.setDeathDate(null);
        targetResident.setDeathPlaceCode(null);
        targetResident.setDeathPlaceAddress(null);

        residentRepository.save(targetResident);

        Report report = getReport(Report.TypeCode.사망, targetResident.getSerialNumber(), serialNumber);
        reportRepository.delete(report);
    }
}
