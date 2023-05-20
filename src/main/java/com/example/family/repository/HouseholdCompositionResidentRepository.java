package com.example.family.repository;

import com.example.family.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdCompositionResidentRepository
        extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.HouseholdResidentPk>,
        HouseholdCompositionResidentRepositoryCustom {
    boolean existsAllByHouseholdSerialNumberAndResidentSerialNumberNot(int householderSerialNumber,
                                                                       int residentSerialNumber);

    void deleteByResident_serialNumber(int residentSerialNumber);
    HouseholdCompositionResident getByPk_ResidentSerialNumber(int residentSerialNumber);
}
