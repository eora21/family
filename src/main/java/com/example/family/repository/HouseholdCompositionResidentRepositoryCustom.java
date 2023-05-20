package com.example.family.repository;

import com.example.family.domain.report.household.HouseholdCompositionResidentDto;

import java.util.List;

public interface HouseholdCompositionResidentRepositoryCustom {
    List<HouseholdCompositionResidentDto> getHouseholdCompositionResidents(int householdSerialNumber);
}
