package com.example.family.domain.report.model.dto;

import com.example.family.domain.certificate_issue.model.dto.Certification;
import com.example.family.domain.report.entity.Report;
import com.example.family.domain.resident.entity.Resident;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeathReportDto implements Certification {
    private LocalDate reportDate;
    private String name;
    private String registrationNumber;
    private LocalDateTime deathDateTime;
    private Resident.DeathPlace deathPlace;
    private String deathPlaceAddress;
    private String reportResidentName;
    private String reportRegistrationNumber;
    private Report.DeathReportQualificationsCode deathReportQualificationsCode;
    private String email;
    private String phoneNumber;
}
