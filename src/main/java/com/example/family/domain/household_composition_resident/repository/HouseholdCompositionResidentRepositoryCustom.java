package com.example.family.domain.household_composition_resident.repository;

import com.example.family.domain.household_composition_resident.model.dto.HouseholdCompositionResidentDto;

import java.util.List;

public interface HouseholdCompositionResidentRepositoryCustom {
    List<HouseholdCompositionResidentDto> getHouseholdCompositionResidents(int householdSerialNumber);
}
