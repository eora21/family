package com.example.family.domain.report.family;

import com.example.family.entity.FamilyRelationship;
import com.example.family.entity.Resident;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
