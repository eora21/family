package com.example.family.controller;

import com.example.family.domain.FamilyRelationshipForm;
import com.example.family.entity.FamilyRelationship;
import com.example.family.entity.Resident;
import com.example.family.service.FamilyRelationshipService;
import com.example.family.service.ResidentService;
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

    private final ResidentService residentService;
    private final FamilyRelationshipService relationshipService;

    @ModelAttribute
    private Resident getBaseResident(@PathVariable int serialNumber) {
        return residentService.getResident(serialNumber);
    }

    @PostMapping
    private HttpEntity<Void> addRelation(@ModelAttribute Resident baseResident,
                                         @RequestBody FamilyRelationshipForm form) {
        Resident familyResident = residentService.getResident(form.getFamilySerialNumber());
        relationshipService.saveRelation(baseResident, familyResident, form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{familySerialNumber}")
    private HttpEntity<Void> updateRelation(@ModelAttribute Resident baseResident, @PathVariable int familySerialNumber,
                                            @RequestBody Map<String, FamilyRelationship.Relationship> form) {
        Resident familyResident = residentService.getResident(familySerialNumber);
        relationshipService.updateRelation(baseResident, familyResident, form.get("relationship"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{familySerialNumber}")
    private HttpEntity<Void> deleteRelation(@ModelAttribute Resident baseResident,
                                            @PathVariable int familySerialNumber) {
        Resident familyResident = residentService.getResident(familySerialNumber);
        relationshipService.deleteRelation(baseResident, familyResident);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
