package com.example.family.domain.household_composition_resident.model.form;

import com.example.family.domain.household_composition_resident.entity.HouseholdCompositionResident;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseholdCompositionResidentEditForm {
    private LocalDate reportDate;
    private HouseholdCompositionResident.RelationshipCode householdRelationshipCode;
    private HouseholdCompositionResident.CompositionChangeReasonCode householdCompositionChangeReasonCode;
}
