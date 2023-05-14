package com.example.family.service;

import com.example.family.domain.HouseholdMovementAddressEditForm;
import com.example.family.domain.HouseholdMovementAddressForm;
import com.example.family.entity.Household;
import com.example.family.entity.HouseholdMovementAddress;
import com.example.family.repository.HouseholdMovementAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository repository;

    public void insertHouseholdMovementAddress(Household household, HouseholdMovementAddressForm form) {
        HouseholdMovementAddress.Pk pk =
                new HouseholdMovementAddress.Pk(form.getReportDate(), household.getSerialNumber());

        HouseholdMovementAddress householdMovementAddress = HouseholdMovementAddress.builder()
                .pk(pk)
                .household(household)
                .address(form.getAddress())
                .lastAddress(form.isLastAddress())
                .build();

        repository.save(householdMovementAddress);
    }

    public HouseholdMovementAddress getHouseholdMovementAddress(LocalDate reportDate, int householdSerialNumber) {
        return repository.findById(new HouseholdMovementAddress.Pk(reportDate, householdSerialNumber))
                .orElseThrow(() -> new IllegalArgumentException("일치하는 데이터가 없습니다."));
    }

    public void modifyHouseholdMovementAddress(Household household, LocalDate reportDate,
                                               HouseholdMovementAddressEditForm form) {
        HouseholdMovementAddress householdMovementAddress =
                getHouseholdMovementAddress(reportDate, household.getSerialNumber());

        updateHouseholdMovementAddress(householdMovementAddress, form);
        repository.save(householdMovementAddress);
    }

    private void updateHouseholdMovementAddress(HouseholdMovementAddress householdMovementAddress,
                                                HouseholdMovementAddressEditForm form) {
        householdMovementAddress.setAddress(form.getAddress());
        householdMovementAddress.setLastAddress(form.isLastAddress());
    }

    public void deleteHouseholdMovementAddress(Household household, LocalDate reportDate) {
        HouseholdMovementAddress householdMovementAddress =
                getHouseholdMovementAddress(reportDate, household.getSerialNumber());
        repository.delete(householdMovementAddress);
    }
}
