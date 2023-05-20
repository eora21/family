package com.example.family.domain.report.family;

import com.example.family.domain.report.ReportDto;
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
