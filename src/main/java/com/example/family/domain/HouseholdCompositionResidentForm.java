package com.example.family.domain;

import com.example.family.entity.HouseholdCompositionResident;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseholdCompositionResidentForm {
    private int residentSerialNumber;
    private LocalDate reportDate;
    private HouseholdCompositionResident.RelationshipCode householdRelationshipCode;
    private HouseholdCompositionResident.CompositionChangeReasonCode householdCompositionChangeReasonCode;
}
