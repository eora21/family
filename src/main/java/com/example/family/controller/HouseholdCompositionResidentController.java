package com.example.family.controller;

import com.example.family.domain.HouseholdCompositionResidentEditForm;
import com.example.family.domain.HouseholdCompositionResidentForm;
import com.example.family.entity.Household;
import com.example.family.service.HouseholdCompositionResidentService;
import com.example.family.service.HouseholdService;
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
@RequestMapping("/household/{householdSerialNumber}/composition")
public class HouseholdCompositionResidentController {
    private final HouseholdService householdService;
    private final HouseholdCompositionResidentService householdCompositionResidentService;

    @ModelAttribute
    public Household getHousehold(@PathVariable int householdSerialNumber) {
        return householdService.getHousehold(householdSerialNumber);
    }

    @PostMapping
    public HttpEntity<Void> insertComposition(@ModelAttribute Household household,
                                              @RequestBody HouseholdCompositionResidentForm form) {
        householdCompositionResidentService.insertComposition(household, form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{residentSerialNumber}")
    public HttpEntity<Void> updateComposition(@ModelAttribute Household household,
                                              @PathVariable int residentSerialNumber,
                                              @RequestBody HouseholdCompositionResidentEditForm form) {
        householdCompositionResidentService.modifyComposition(household, residentSerialNumber, form);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{residentSerialNumber}")
    public HttpEntity<Void> deleteComposition(@ModelAttribute Household household,
                                              @PathVariable int residentSerialNumber) {
        householdCompositionResidentService.deleteComposition(household, residentSerialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
