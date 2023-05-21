package com.example.family.domain.family_relationship.model.dto;

import com.example.family.domain.certificate_issue.model.dto.Certification;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FamilyRelationshipCertification implements Certification {
    private String registrationBaseAddress;
    private List<FamilyRelationshipDto> familyRelationships;
}
