package com.example.family.domain.certificate_issue.model.dto;

import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CertificateDto {
    private Long certificateConfirmationNumber;
    private LocalDate certificateIssueDate;
    private CertificateIssue.CertificateType certificateType;
    private Certification certification;
}
