package com.example.family.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class HouseholdMovementAddressForm {
    private LocalDate reportDate;
    private String address;
    private boolean lastAddress;
}
