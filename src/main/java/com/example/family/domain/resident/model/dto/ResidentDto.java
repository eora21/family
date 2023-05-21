package com.example.family.domain.resident.model.dto;

import com.example.family.domain.resident.entity.Resident;

import java.time.LocalDateTime;

public interface ResidentDto {
    String getName();

    String getRegistrationNumber();

    String getGender();

    LocalDateTime getBirthDate();

    Resident.BirthPlace getBirthPlaceCode();

    String getRegistrationBaseAddress();

    LocalDateTime getDeathDate();

    Resident.DeathPlace getDeathPlaceCode();

    String getDeathPlaceAddress();
}
