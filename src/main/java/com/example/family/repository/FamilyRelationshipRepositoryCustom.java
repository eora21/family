package com.example.family.repository;

import com.example.family.domain.report.family.FamilyRelationshipDto;

import java.util.List;

public interface FamilyRelationshipRepositoryCustom {
    List<FamilyRelationshipDto> getMyFamilyRelationship(int residentSerialNumber);
}
