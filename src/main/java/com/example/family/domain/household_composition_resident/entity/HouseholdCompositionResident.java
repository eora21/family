package com.example.family.domain.household_composition_resident.entity;

import com.example.family.domain.household.entity.Household;
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
@Table(name = "household_composition_resident")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
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

    @Setter
    @Column(name = "report_date")
    private LocalDate reportDate;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "household_relationship_code")
    private RelationshipCode relationshipCode;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "household_composition_change_reason_code")
    private CompositionChangeReasonCode compositionChangeReasonCode;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class HouseholdResidentPk implements Serializable {
        Integer householdSerialNumber;
        Integer residentSerialNumber;
    }

    public enum RelationshipCode {
        본인, 배우자, 자녀, 동거인
    }

    public enum CompositionChangeReasonCode {
        세대분리, 전입, 출생등록
    }
}
