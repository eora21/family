package com.example.family.domain.report.household;

import com.example.family.domain.report.ReportDto;
import com.example.family.entity.Household;
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
