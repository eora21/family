package com.example.family.domain.household.repository;

import com.example.family.domain.household.entity.Household;
import com.example.family.domain.household.model.dto.HouseholdSerialNumberDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

    void deleteAllByResident_serialNumber(int residentSerialNumber);

    default Household getHousehold(int serialNumber) {
        return findById(serialNumber)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 세대가 존재하지 않습니다."));
    }
}
