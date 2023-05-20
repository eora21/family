package com.example.family.domain.report.household;

import com.example.family.entity.HouseholdMovementAddress;

import java.time.LocalDate;

public interface HouseholdMovementAddressDto {
    LocalDate getPkReportDate();
    String getAddress();
    HouseholdMovementAddress.LastAddress getLastAddress();
}
