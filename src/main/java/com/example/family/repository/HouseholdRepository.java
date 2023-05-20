package com.example.family.repository;

import com.example.family.entity.Household;
import com.example.family.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

    List<Integer> deleteByResident_serialNumber(int residentSerialNumber);
}
