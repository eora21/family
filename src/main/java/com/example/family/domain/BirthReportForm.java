package com.example.family.domain;

import com.example.family.entity.Report;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BirthReportForm {
    private Integer targetSerialNumber;
    private LocalDate reportDate;
    private Report.BirthReportQualificationsCode birthReportQualificationsCode;
    private String email;
    private String phoneNumber;
}
