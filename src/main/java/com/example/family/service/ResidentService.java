package com.example.family.service;

import com.example.family.domain.ResidentForm;
import com.example.family.entity.Resident;
import com.example.family.repository.HouseholdCompositionResidentRepository;
import com.example.family.repository.HouseholdRepository;
import com.example.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    public Resident saveResident(ResidentForm residentForm) {
        return residentRepository.save(residentForm.toEntity());
    }

    @Transactional(readOnly = true)
    public List<Resident> findAll() {
        return residentRepository.findAll();
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
        householdRepository.deleteByResident_serialNumber(residentSerialNumber);
        residentRepository.deleteById(residentSerialNumber);
    }
}
