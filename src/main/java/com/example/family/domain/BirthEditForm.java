package com.example.family.domain;

import com.example.family.entity.Report;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BirthEditForm {
    private LocalDate reportDate;
    private Report.BirthReportQualificationsCode birthReportQualificationsCode;
    private String email;
    private String phoneNumber;
}
