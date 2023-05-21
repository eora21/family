package com.example.family.domain.resident.model.dto;

import com.example.family.domain.report.entity.Report;

import java.util.List;

public interface ResidentDto {
    int getSerialNumber();
    String getName();
    List<Report> getReports();
}

