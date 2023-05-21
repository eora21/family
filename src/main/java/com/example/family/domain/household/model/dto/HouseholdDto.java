package com.example.family.domain.household.model.dto;

import com.example.family.domain.certificate_issue.model.dto.Certification;
import com.example.family.domain.household.entity.Household;
import com.example.family.domain.household_composition_resident.model.dto.HouseholdCompositionResidentDto;
import com.example.family.domain.household_movement_address.model.dto.HouseholdMovementAddressDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseholdDto implements Certification {
    private String householdResidentName;
    private Household.CompositionReasonCode compositionReasonCode;
    private LocalDate compositionDate;
    private List<HouseholdMovementAddressDto> householdMovementAddresses;
    private List<HouseholdCompositionResidentDto> householdCompositionResidents;
}
