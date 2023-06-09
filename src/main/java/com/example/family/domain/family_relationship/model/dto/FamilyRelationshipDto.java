package com.example.family.domain.family_relationship.model.dto;

import com.example.family.domain.family_relationship.entity.FamilyRelationship;
import com.example.family.domain.resident.entity.Resident;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamilyRelationshipDto {
    private FamilyRelationship.FamilyRelationshipCode familyRelationshipCode;
    private String name;
    private LocalDateTime birthDate;
    private String residentRegistrationNumber;
    private Resident.Gender gender;

    @QueryProjection
    public FamilyRelationshipDto(FamilyRelationship.FamilyRelationshipCode familyRelationshipCode, String name,
                                 LocalDateTime birthDate, String residentRegistrationNumber, Resident.Gender gender) {
        this.familyRelationshipCode = familyRelationshipCode;
        this.name = name;
        this.birthDate = birthDate;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.gender = gender;
    }
}
