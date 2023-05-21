package com.example.family.domain.household.model.form;

import com.example.family.domain.household.entity.Household;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseholdForm {
    private Integer serialNumber;
    private LocalDate compositionDate;
    private Household.CompositionReasonCode compositionReasonCode;
    private String currentAddress;
}
