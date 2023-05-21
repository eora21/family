package com.example.family.domain.resident.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResidentViewDto {
    private int serialNumber;
    private String name;
    private boolean hasBirthReport;
    private boolean hasDeathReport;

    public static ResidentViewDto newInstance(ResidentDto residentDto) {
        return new ResidentViewDto(
                residentDto.getSerialNumber(),
                residentDto.getName(),
                residentDto.getReports().stream()
                        .anyMatch(report -> Objects.nonNull(report.getBirthReportQualificationsCode())),
                residentDto.getReports().stream()
                        .anyMatch(report -> Objects.nonNull(report.getDeathReportQualificationsCode()))
        );
    }
}
