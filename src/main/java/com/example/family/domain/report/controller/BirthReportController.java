package com.example.family.domain.report.controller;

import com.example.family.domain.report.model.form.BirthEditForm;
import com.example.family.domain.report.model.form.BirthReportForm;
import com.example.family.domain.resident.entity.Resident;
import com.example.family.domain.resident.repository.ResidentRepository;
import com.example.family.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthReportController {
    private final ResidentRepository residentRepository;
    private final ReportService reportService;

    @ModelAttribute
    private Resident getResident(@PathVariable int serialNumber) {
        return residentRepository.getReferenceById(serialNumber);
    }

    @PostMapping
    public HttpEntity<Void> insertBirthReport(@ModelAttribute Resident resident, @RequestBody BirthReportForm form) {
        reportService.insertBirthReport(resident, form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{targetSerialNumber}")
    public HttpEntity<Void> editBirthReport(@ModelAttribute Resident resident, @PathVariable int targetSerialNumber,
                                            @RequestBody BirthEditForm form) {
        Resident targetResident = residentRepository.getReferenceById(targetSerialNumber);
        reportService.modifyBirthReport(resident, targetResident, form);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{targetSerialNumber}")
    public HttpEntity<Void> deleteBirthReport(@ModelAttribute Resident resident, @PathVariable int targetSerialNumber) {
        reportService.deleteBirthReport(resident.getSerialNumber(), targetSerialNumber);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
