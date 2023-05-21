package com.example.family.domain.household_movement_address.model.form;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseholdMovementAddressForm {
    private LocalDate reportDate;
    private String address;
}
