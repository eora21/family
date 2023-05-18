package com.example.family.controller;

import com.example.family.domain.DeathEditForm;
import com.example.family.domain.DeathReportForm;
import com.example.family.entity.Resident;
import com.example.family.repository.ResidentRepository;
import com.example.family.service.ReportService;
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
@RequestMapping("/residents/{serialNumber}/death")
public class DeathReportController {
    private final ResidentRepository repository;
    private final ReportService reportService;

    @ModelAttribute
    private Resident getResident(@PathVariable int serialNumber) {
        return repository.getReferenceById(serialNumber);
    }

    @PostMapping
    public HttpEntity<Void> insertDeathReport(@ModelAttribute Resident resident, @RequestBody DeathReportForm form) {
        Resident targetResident = repository.getReferenceById(form.getTargetSerialNumber());
        reportService.insertDeathReport(resident, targetResident, form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{targetSerialNumber}")
    public HttpEntity<Void> editDeathReport(@ModelAttribute Resident resident, @PathVariable int targetSerialNumber,
                                            @RequestBody DeathEditForm form) {
        Resident targetResident = repository.getReferenceById(targetSerialNumber);
        reportService.modifyDeathReport(resident, targetResident, form);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{targetSerialNumber}")
    public HttpEntity<Void> deleteDeathReport(@ModelAttribute Resident resident, @PathVariable int targetSerialNumber) {
        Resident targetResident = repository.getReferenceById(targetSerialNumber);
        reportService.deleteDeathReport(resident.getSerialNumber(), targetResident);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
