package com.example.family.domain;

import com.example.family.entity.Report;
import com.example.family.entity.Resident;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeathEditForm {
    private LocalDate reportDate;
    private Report.DeathReportQualificationsCode deathReportQualificationsCode;
    private String email;
    private String phoneNumber;
    private LocalDateTime deathDate;
    private Resident.DeathPlace deathPlaceCode;
    private String deathPlaceAddress;
}
