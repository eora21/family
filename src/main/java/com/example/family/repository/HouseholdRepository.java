package com.example.family.repository;

import com.example.family.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
}
