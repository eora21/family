package com.example.family.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class HouseholdMovementAddressEditForm {
    private String address;
    private boolean lastAddress;
}
