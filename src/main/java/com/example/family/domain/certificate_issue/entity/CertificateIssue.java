package com.example.family.domain.certificate_issue.entity;

import com.example.family.domain.resident.entity.Resident;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "certificate_issue")
public class CertificateIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_confirmation_number")
    private Long confirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "certificate_type_code")
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;

    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;

    public enum CertificateType {
        가족관계증명서, 주민등록등본, 출생신고서, 사망신고서
    }

    @Builder
    private CertificateIssue(Resident resident, CertificateType certificateType, LocalDate certificateIssueDate) {
        this.resident = resident;
        this.certificateType = certificateType;
        this.certificateIssueDate = certificateIssueDate;
    }
}
