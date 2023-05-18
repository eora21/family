package com.example.family.repository;

import com.example.family.entity.Household;
import com.example.family.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {

    @Query("select h " +
            "from HouseholdMovementAddress h " +
            "where h.household = :household and h.lastAddress = 'Y'")
    List<HouseholdMovementAddress> findAllByHouseholdAndLastAddress_Y(@Param("household") Household household);

}
