package com.example.family.domain.certificate_issue.model.dto;

import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CertificateIssueDto {
    private String residentName;
    private String residentRegistrationNumber;
    private CertificateIssue.CertificateType certificateType;
    private LocalDate certificateIssueDate;

    public static CertificateIssueDto newInstance(CertificateIssue certificateIssue) {
        return new CertificateIssueDto(certificateIssue.getResident().getName(),
                certificateIssue.getResident().getRegistrationNumber(), certificateIssue.getCertificateType(),
                certificateIssue.getCertificateIssueDate());
    }
}
