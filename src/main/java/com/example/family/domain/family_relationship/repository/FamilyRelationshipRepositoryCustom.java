package com.example.family.domain.family_relationship.repository;

import com.example.family.domain.family_relationship.model.dto.FamilyRelationshipDto;

import java.util.List;

public interface FamilyRelationshipRepositoryCustom {
    List<FamilyRelationshipDto> getMyFamilyRelationship(int residentSerialNumber);
}
