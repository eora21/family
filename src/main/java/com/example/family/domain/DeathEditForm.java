package com.example.family.domain;

import com.example.family.entity.Report;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeathEditForm {
    private LocalDate reportDate;
    private Report.DeathReportQualificationsCode deathReportQualificationsCode;
    private String email;
    private String phoneNumber;
}
