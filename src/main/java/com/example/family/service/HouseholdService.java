package com.example.family.service;

import com.example.family.domain.HouseholdForm;
import com.example.family.entity.Household;
import com.example.family.entity.Resident;
import com.example.family.repository.HouseholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseholdService {
    private final HouseholdRepository householdRepository;

    public void insertHousehold(HouseholdForm form, Resident resident) {
        Household household = Household.builder()
                .resident(resident)
                .compositionDate(form.getCompositionDate())
                .compositionReasonCode(form.getCompositionReasonCode())
                .currentAddress(form.getCurrentAddress())
                .build();

        householdRepository.save(household);
    }

    public void deleteHousehold(int householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);
    }

    public Household getHousehold(int householdSerialNumber) {
        return householdRepository.findById(householdSerialNumber)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 데이터가 없습니다."));
    }
}
