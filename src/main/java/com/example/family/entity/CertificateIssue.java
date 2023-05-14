package com.example.family.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "certificate_issue")
public class CertificateIssue {

    @Id
    @Column(name = "certificate_confirmation_number")
    private Long confirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "certificate_type_code")
    private CertificateType certificateType;

    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;

    public enum CertificateType {
        가족관계증명서, 주민등록등본,
    }

}
