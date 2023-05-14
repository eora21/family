package com.example.family.repository;

import com.example.family.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
}
