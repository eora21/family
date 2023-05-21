package com.example.family.domain.resident.service;

import com.example.family.domain.household.model.dto.HouseholdSerialNumberDto;
import com.example.family.domain.resident.model.dto.ResidentDto;
import com.example.family.domain.resident.model.dto.ResidentViewDto;
import com.example.family.domain.resident.model.form.ResidentForm;
import com.example.family.domain.resident.entity.Resident;
import com.example.family.domain.household_composition_resident.repository.HouseholdCompositionResidentRepository;
import com.example.family.domain.household_movement_address.repository.HouseholdMovementAddressRepository;
import com.example.family.domain.household.repository.HouseholdRepository;
import com.example.family.domain.resident.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    public Resident saveResident(ResidentForm residentForm) {
        return residentRepository.save(residentForm.toEntity());
    }

    @Transactional(readOnly = true)
    public Page<ResidentViewDto> findAll(Pageable pageable) {
        Page<ResidentDto> residents = residentRepository.findAllDtoBy(pageable);
        return residents.map(ResidentViewDto::newInstance);
    }

    public void modifyResident(int serialNumber, ResidentForm residentForm) {
        Resident resident = residentRepository.getReferenceById(serialNumber);
        updateResident(residentForm, resident);
        residentRepository.save(resident);
    }

    private static void updateResident(ResidentForm residentForm, Resident resident) {
        resident.setName(residentForm.getName());
        resident.setRegistrationNumber(residentForm.getRegistrationNumber());
        resident.setGender(residentForm.getGender());
        resident.setBirthDate(residentForm.getBirthDate());
        resident.setBirthPlaceCode(residentForm.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentForm.getRegistrationBaseAddress());
    }

    public void deleteResident(int residentSerialNumber) {
        if (residentRepository.hasFamily(residentSerialNumber)) {
            throw new IllegalStateException("남은 가족이 있어 삭제가 불가능합니다.");
        }

        householdCompositionResidentRepository.deleteByResident_serialNumber(residentSerialNumber);
        householdRepository.deleteAllByResident_serialNumber(residentSerialNumber);
        residentRepository.deleteById(residentSerialNumber);
    }
}
