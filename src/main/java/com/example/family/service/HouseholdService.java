package com.example.family.service;

import com.example.family.domain.HouseholdCompositionResidentForm;
import com.example.family.domain.HouseholdForm;
import com.example.family.domain.HouseholdMovementAddressForm;
import com.example.family.entity.Household;
import com.example.family.entity.HouseholdCompositionResident;
import com.example.family.entity.Resident;
import com.example.family.repository.HouseholdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HouseholdService {
    private final HouseholdMovementAddressService householdMovementAddressService;
    private final HouseholdCompositionResidentService householdCompositionResidentService;
    private final HouseholdRepository householdRepository;

    @Transactional
    public void insertHousehold(HouseholdForm form, Resident resident) {
        Household household = Household.builder()
                .resident(resident)
                .compositionDate(form.getCompositionDate())
                .compositionReasonCode(form.getCompositionReasonCode())
                .currentAddress(form.getCurrentAddress())
                .build();

        Household savedHousehold = householdRepository.save(household);

        householdMovementAddressService.insertHouseholdMovementAddress(savedHousehold,
                new HouseholdMovementAddressForm(form.getCompositionDate(), form.getCurrentAddress()));

        householdCompositionResidentService.insertComposition(savedHousehold, new HouseholdCompositionResidentForm(
                resident.getSerialNumber(),
                form.getCompositionDate(),
                HouseholdCompositionResident.RelationshipCode.본인,
                HouseholdCompositionResident.CompositionChangeReasonCode.세대분리
        ));
    }

    @Transactional
    public void deleteHousehold(int householdSerialNumber) {
        Household household = householdRepository.getReferenceById(householdSerialNumber);
        boolean isExist = householdCompositionResidentService.checkAnotherResident(
                householdSerialNumber, household.getResident().getSerialNumber());

        if (isExist) {
            throw new IllegalStateException("남은 가족이 있어 삭제가 불가능합니다.");
        }

        householdRepository.deleteById(householdSerialNumber);
    }

    public Household getHousehold(int householdSerialNumber) {
        return householdRepository.findById(householdSerialNumber)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 데이터가 없습니다."));
    }
}
