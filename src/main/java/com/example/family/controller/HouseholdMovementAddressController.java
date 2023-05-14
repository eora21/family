package com.example.family.controller;

import com.example.family.domain.HouseholdMovementAddressEditForm;
import com.example.family.domain.HouseholdMovementAddressForm;
import com.example.family.entity.Household;
import com.example.family.service.HouseholdMovementAddressService;
import com.example.family.service.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementAddressController {
    private final HouseholdService householdService;
    private final HouseholdMovementAddressService householdMovementAddressService;

    @ModelAttribute
    public Household getHousehold(@PathVariable int householdSerialNumber) {
        return householdService.getHousehold(householdSerialNumber);
    }

    @PostMapping
    public HttpEntity<Void> insertHouseholdMovementAddress(@ModelAttribute Household household,
                                                           HouseholdMovementAddressForm form) {
        householdMovementAddressService.insertHouseholdMovementAddress(household, form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{reportDate}")
    public HttpEntity<Void> modifyHouseholdMovementAddress(@ModelAttribute Household household,
                                                           HouseholdMovementAddressEditForm form,
                                                           @PathVariable LocalDate reportDate) {
        householdMovementAddressService.modifyHouseholdMovementAddress(household, reportDate, form);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{reportDate}")
    public HttpEntity<Void> deleteHouseholdMovementAddress(@ModelAttribute Household household,
                                                           @PathVariable LocalDate reportDate) {
        householdMovementAddressService.deleteHouseholdMovementAddress(household, reportDate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
