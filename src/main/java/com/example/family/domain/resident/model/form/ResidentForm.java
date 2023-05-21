package com.example.family.domain.resident.model.form;

import com.example.family.domain.resident.entity.Resident;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResidentForm {
    private String name;
    private String registrationNumber;
    private Resident.Gender gender;
    private LocalDateTime birthDate;
    private Resident.BirthPlace birthPlaceCode;
    private String registrationBaseAddress;

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
