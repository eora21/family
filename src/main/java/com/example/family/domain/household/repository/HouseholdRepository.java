package com.example.family.domain.household.repository;

import com.example.family.domain.household.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

    List<Integer> deleteByResident_serialNumber(int residentSerialNumber);
}
