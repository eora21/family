package com.example.family.controller;

import com.example.family.domain.HouseholdForm;
import com.example.family.entity.Resident;
import com.example.family.repository.ResidentRepository;
import com.example.family.service.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/household")
public class HouseholdController {
    private final ResidentRepository residentRepository;
    private final HouseholdService householdService;

    @PostMapping
    public HttpEntity<Void> insertHousehold(@RequestBody HouseholdForm form) {
        Resident resident = residentRepository.getReferenceById(form.getSerialNumber());
        householdService.insertHousehold(form, resident);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{householdSerialNumber}")
    public HttpEntity<Void> deleteHousehold(@PathVariable int householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
