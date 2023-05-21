package com.example.family.domain.certificate_issue.model.dto;

import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import com.example.family.domain.report.model.dto.ReportDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CertificateDto {
    private Long certificateConfirmationNumber;
    private LocalDate certificateIssueDate;
    @Getter
    private CertificateIssue.CertificateType certificateType;
    private ReportDto reportDto;
}
