package com.example.family.domain.report.model.form;

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
public class DeathReportForm {
    private Integer targetSerialNumber;
    private LocalDate reportDate;
    private Report.DeathReportQualificationsCode deathReportQualificationsCode;
    private String email;
    private String phoneNumber;
    private LocalDateTime deathDate;
    private Resident.DeathPlace deathPlaceCode;
    private String deathPlaceAddress;
}
