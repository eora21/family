package com.example.family.domain.family_relationship.controller;

import com.example.family.domain.family_relationship.model.form.FamilyRelationshipForm;
import com.example.family.domain.family_relationship.entity.FamilyRelationship;
import com.example.family.domain.resident.entity.Resident;
import com.example.family.domain.resident.repository.ResidentRepository;
import com.example.family.domain.family_relationship.service.FamilyRelationshipService;
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

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationshipController {

    private final ResidentRepository residentRepository;
    private final FamilyRelationshipService relationshipService;

    @ModelAttribute
    private Resident getBaseResident(@PathVariable int serialNumber) {
        return residentRepository.getReferenceById(serialNumber);
    }

    @PostMapping
    private HttpEntity<Void> addRelation(@ModelAttribute Resident baseResident,
                                         @RequestBody FamilyRelationshipForm form) {
        Resident familyResident = residentRepository.getReferenceById(form.getFamilySerialNumber());
        relationshipService.saveRelation(baseResident, familyResident, form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{familySerialNumber}")
    private HttpEntity<Void> updateRelation(@ModelAttribute Resident baseResident, @PathVariable int familySerialNumber,
                                            @RequestBody Map<String, FamilyRelationship.FamilyRelationshipCode> form) {
        Resident familyResident = residentRepository.getReferenceById(familySerialNumber);
        relationshipService.updateRelation(baseResident, familyResident, form.get("relationship"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{familySerialNumber}")
    private HttpEntity<Void> deleteRelation(@ModelAttribute Resident baseResident,
                                            @PathVariable int familySerialNumber) {
        Resident familyResident = residentRepository.getReferenceById(familySerialNumber);
        relationshipService.deleteRelation(baseResident, familyResident);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
