package com.example.family.domain.household.model.dto;

import com.example.family.domain.report.model.dto.ReportDto;
import com.example.family.domain.household.entity.Household;
import com.example.family.domain.household_composition_resident.model.dto.HouseholdCompositionResidentDto;
import com.example.family.domain.household_movement_address.model.dto.HouseholdMovementAddressDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseholdDto implements ReportDto {
    private String householdResidentName;
    private Household.CompositionReasonCode compositionReasonCode;
    private List<HouseholdMovementAddressDto> householdMovementAddresses;
    private List<HouseholdCompositionResidentDto> householdCompositionResidents;
}
