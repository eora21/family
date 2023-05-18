package com.example.family.service;

import com.example.family.domain.HouseholdCompositionResidentEditForm;
import com.example.family.domain.HouseholdCompositionResidentForm;
import com.example.family.entity.Household;
import com.example.family.entity.HouseholdCompositionResident;
import com.example.family.repository.HouseholdCompositionResidentRepository;
import com.example.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HouseholdCompositionResidentService {
    private final ResidentRepository residentresidentRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Transactional
    public void insertComposition(Household household, HouseholdCompositionResidentForm form) {
        HouseholdCompositionResident composition = HouseholdCompositionResident.builder()
                .pk(new HouseholdCompositionResident.HouseholdResidentPk(
                        household.getSerialNumber(), form.getResidentSerialNumber()))
                .household(household)
                .resident(residentresidentRepository.getReferenceById(form.getResidentSerialNumber()))
                .reportDate(form.getReportDate())
                .relationshipCode(form.getHouseholdRelationshipCode())
                .compositionChangeReasonCode(form.getHouseholdCompositionChangeReasonCode())
                .build();

        householdCompositionResidentRepository.save(composition);
    }

    @Transactional(readOnly = true)
    public boolean checkAnotherResident(int householdSerialNumber, int residentSerialNumber) {
        return householdCompositionResidentRepository.existsAllByHouseholdSerialNumberAndResidentSerialNumberNot(
                householdSerialNumber, residentSerialNumber);
    }

    @Transactional
    public void modifyComposition(Household household, int residentSerialNumber,
                                  HouseholdCompositionResidentEditForm form) {
        HouseholdCompositionResident composition = householdCompositionResidentRepository.getReferenceById(
                new HouseholdCompositionResident.HouseholdResidentPk(
                        household.getSerialNumber(), residentSerialNumber));

        composition.setReportDate(form.getReportDate());
        composition.setRelationshipCode(form.getHouseholdRelationshipCode());
        composition.setCompositionChangeReasonCode(form.getHouseholdCompositionChangeReasonCode());
    }

    @Transactional
    public void deleteComposition(Household household, int residentSerialNumber) {
        householdCompositionResidentRepository.deleteById( new HouseholdCompositionResident.HouseholdResidentPk(
                household.getSerialNumber(), residentSerialNumber));
    }
}
