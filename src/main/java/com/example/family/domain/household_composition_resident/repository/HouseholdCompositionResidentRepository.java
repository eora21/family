package com.example.family.domain.household_composition_resident.repository;

import com.example.family.domain.household_composition_resident.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseholdCompositionResidentRepository
        extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdResidentPk>,
        HouseholdCompositionResidentRepositoryCustom {
    boolean existsAllByHouseholdSerialNumberAndResidentSerialNumberNot(int householderSerialNumber,
                                                                       int residentSerialNumber);

    void deleteByResident_serialNumber(int residentSerialNumber);
    Optional<HouseholdCompositionResident> findByPk_ResidentSerialNumber(int residentSerialNumber);
    default HouseholdCompositionResident getByPk_ResidentSerialNumber(int residentSerialNumber) {
        return findByPk_ResidentSerialNumber(residentSerialNumber)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 세대가 존재하지 않습니다."));
    }

}
