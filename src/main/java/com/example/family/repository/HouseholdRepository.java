package com.example.family.repository;

import com.example.family.entity.Household;
import com.example.family.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    boolean existsByResident(Resident resident);

    void deleteByResident_serialNumber(int residentSerialNumber);
}
