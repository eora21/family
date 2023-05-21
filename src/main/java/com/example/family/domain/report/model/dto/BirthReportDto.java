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
public class BirthReportDto implements Certification {
    private LocalDate reportDate;
    private String name;
    private Resident.Gender gender;
    private LocalDateTime birthDateTime;
    private Resident.BirthPlace birthPlace;
    private String registrationBaseAddress;
    private String fatherName;
    private String fatherRegistrationNumber;
    private String motherName;
    private String motherRegistrationNumber;
    private String reportResidentName;
    private String reportRegistrationNumber;
    private Report.BirthReportQualificationsCode birthReportQualificationsCode;
    private String email;
    private String phoneNumber;
}
