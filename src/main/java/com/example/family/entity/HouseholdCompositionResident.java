package com.example.family.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "household_composition_resident")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseholdCompositionResident {

    @EmbeddedId
    private HouseholdResidentPk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @Column(name = "household_relationship_code")
    private RelationshipCode relationshipCode;

    @Column(name = "household_composition_change_reason_code")
    private CompositionChangeReasonCode compositionChangeReasonCode;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class HouseholdResidentPk implements Serializable {
        @Column(name = "household_serial_number")
        Integer householdSerialNumber;

        @Column(name = "resident_serial_number")
        Integer residentSerialNumber;
    }

    public enum RelationshipCode {
        본인, 배우자, 자녀, 동거인
    }

    public enum CompositionChangeReasonCode {
        세대분리, 전입, 출생등록
    }
}
