package com.example.family.domain.report.household;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.example.family.entity.HouseholdCompositionResident.CompositionChangeReasonCode;
import static com.example.family.entity.HouseholdCompositionResident.RelationshipCode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseholdCompositionResidentDto {
    private RelationshipCode relationshipCode;
    private String name;
    private String residentRegistrationNumber;
    private LocalDate reportDate;
    private CompositionChangeReasonCode compositionChangeReasonCode;

    @QueryProjection
    public HouseholdCompositionResidentDto(RelationshipCode relationshipCode, String name,
                                           String residentRegistrationNumber, LocalDate reportDate,
                                           CompositionChangeReasonCode compositionChangeReasonCode) {
        this.relationshipCode = relationshipCode;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.reportDate = reportDate;
        this.compositionChangeReasonCode = compositionChangeReasonCode;
    }
}
