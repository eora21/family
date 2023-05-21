package com.example.family.domain.household_movement_address.repository;

import com.example.family.domain.household_movement_address.model.dto.HouseholdMovementAddressDto;
import com.example.family.domain.household.entity.Household;
import com.example.family.domain.household_movement_address.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {

    @Query("select h " +
            "from HouseholdMovementAddress h " +
            "where h.household = :household and h.lastAddress = 'Y'")
    List<HouseholdMovementAddress> findAllByHouseholdAndLastAddress_Y(@Param("household") Household household);

    @Query("delete HouseholdMovementAddress h where h.pk.householdSerialNumber in :serialNumbers")
    void deleteAllByPk_HouseholdSerialNumber(@Param("serialNumbers") List<Integer> serialNumbers);

    List<HouseholdMovementAddressDto> findAllByHousehold(Household household);

}
