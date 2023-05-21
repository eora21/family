package com.example.family.domain.household_movement_address.model.dto;

import com.example.family.domain.household_movement_address.entity.HouseholdMovementAddress;

import java.time.LocalDate;

public interface HouseholdMovementAddressDto {
    LocalDate getPkReportDate();
    String getAddress();
    HouseholdMovementAddress.LastAddress getLastAddress();
}
