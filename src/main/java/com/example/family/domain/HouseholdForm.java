package com.example.family.domain;

import com.example.family.entity.Household;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class HouseholdForm {
    private final Integer serialNumber;
    private final LocalDate compositionDate;
    private final Household.CompositionReasonCode compositionReasonCode;
    private final String currentAddress;
}
