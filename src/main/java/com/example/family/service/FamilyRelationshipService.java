package com.example.family.service;

import com.example.family.domain.FamilyRelationshipForm;
import com.example.family.entity.FamilyRelationship;
import com.example.family.entity.Resident;
import com.example.family.repository.FamilyRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FamilyRelationshipService {
    private final FamilyRelationshipRepository repository;

    @Transactional
    public void saveRelation(Resident baseResident, Resident familyResident, FamilyRelationshipForm form) {
        FamilyRelationship.FamilyRelationshipPk familyRelationshipPk =
                new FamilyRelationship.FamilyRelationshipPk(familyResident.getSerialNumber(), baseResident.getSerialNumber());

        if (repository.existsById(familyRelationshipPk)) {
            throw new IllegalStateException("해당하는 관계가 이미 설정되어 있습니다.");
        }

        FamilyRelationship familyRelationship = FamilyRelationship.builder()
                .pk(familyRelationshipPk)
                .baseResident(baseResident)
                .familyResident(familyResident)
                .code(form.getRelationship())
                .build();

        repository.save(familyRelationship);
    }

    public FamilyRelationship getFamilyRelationship(int familyResidentNumber, int baseResidentNumber) {
        return repository.findById(
                        new FamilyRelationship.FamilyRelationshipPk(familyResidentNumber, baseResidentNumber))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 관계가 없습니다."));
    }

    public void updateRelation(Resident baseResident, Resident familyResident,
                               FamilyRelationship.Relationship relationship) {
        FamilyRelationship familyRelationship =
                getFamilyRelationship(familyResident.getSerialNumber(), baseResident.getSerialNumber());
        familyRelationship.setCode(relationship);
        repository.save(familyRelationship);
    }

    public void deleteRelation(Resident baseResident, Resident familyResident) {
        FamilyRelationship familyRelationship =
                getFamilyRelationship(familyResident.getSerialNumber(), baseResident.getSerialNumber());
        repository.delete(familyRelationship);
    }
}
