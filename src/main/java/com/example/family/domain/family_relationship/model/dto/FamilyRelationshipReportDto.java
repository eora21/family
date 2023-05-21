package com.example.family.domain.family_relationship.model.dto;

import com.example.family.domain.report.model.dto.ReportDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FamilyRelationshipReportDto implements ReportDto {
    private String registrationBaseAddress;
    private List<FamilyRelationshipDto> familyRelationships;
}
