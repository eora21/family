package com.example.family.domain;

import com.example.family.entity.Resident;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ResidentForm {
    private final String name;
    private final String registrationNumber;
    private final Resident.Gender gender;
    private final LocalDateTime birthDate;
    private final Resident.BirthPlace birthPlaceCode;
    private final String registrationBaseAddress;

    public Resident toEntity() {
        return Resident.builder()
                .name(getName())
                .registrationNumber(getRegistrationNumber())
                .gender(getGender())
                .birthDate(getBirthDate())
                .birthPlaceCode(getBirthPlaceCode())
                .registrationBaseAddress(getRegistrationBaseAddress())
                .build();
    }
}
