package com.example.family.domain.report.entity;

import com.example.family.domain.resident.entity.Resident;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "birth_death_report_resident")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Report {
    @EmbeddedId
    private ReportPk pk;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @MapsId("reportResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;

    @Setter
    @Column(name = "birth_death_report_date")
    private LocalDate reportDate;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_report_qualifications_code")
    private BirthReportQualificationsCode birthReportQualificationsCode;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "death_report_qualifications_code")
    private DeathReportQualificationsCode deathReportQualificationsCode;

    @Setter
    @Column(name = "email_address")
    private String emailAddress;

    @Setter
    @Column(name = "phone_number")
    private String phoneNumber;

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReportPk implements Serializable {
        @Enumerated(EnumType.STRING)
        @Column(name = "birth_death_type_code")
        private TypeCode typeCode;

        private Integer residentSerialNumber;

        private Integer reportResidentSerialNumber;
    }

    public enum TypeCode {
        출생, 사망
    }

    public enum BirthReportQualificationsCode {
        부, 모, 호주, 동거친족, 기타
    }

    public enum DeathReportQualificationsCode {
        동거친족, 비동거친족, 동거자, 기타
    }
}
