package com.example.family.domain.household_composition_resident.repository;

import com.example.family.domain.household_composition_resident.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository
        extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdResidentPk>,
        HouseholdCompositionResidentRepositoryCustom {
    boolean existsAllByHouseholdSerialNumberAndResidentSerialNumberNot(int householderSerialNumber,
                                                                       int residentSerialNumber);

    void deleteByResident_serialNumber(int residentSerialNumber);
    HouseholdCompositionResident getByPk_ResidentSerialNumber(int residentSerialNumber);
}
