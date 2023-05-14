package com.example.family.service;

import com.example.family.domain.ResidentForm;
import com.example.family.entity.Resident;
import com.example.family.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository repository;

    public void saveResident(ResidentForm residentForm) {
        repository.save(residentForm.toEntity());
    }

    public List<Resident> findAll() {
        return repository.findAll();
    }

    public Resident getResident(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id값에 일치하는 주민이 없습니다."));
    }

    @Transactional
    public void modifyResident(int serialNumber, ResidentForm residentForm) {
        Resident resident = getResident(serialNumber);
        updateResident(residentForm, resident);
        repository.save(resident);
    }

    private static void updateResident(ResidentForm residentForm, Resident resident) {
        resident.setName(residentForm.getName());
        resident.setRegistrationNumber(residentForm.getRegistrationNumber());
        resident.setGender(residentForm.getGender());
        resident.setBirthDate(residentForm.getBirthDate());
        resident.setBirthPlaceCode(residentForm.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentForm.getRegistrationBaseAddress());
    }
}
