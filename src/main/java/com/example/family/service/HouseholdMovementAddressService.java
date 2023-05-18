package com.example.family.service;

import com.example.family.domain.HouseholdMovementAddressEditForm;
import com.example.family.domain.HouseholdMovementAddressForm;
import com.example.family.entity.Household;
import com.example.family.entity.HouseholdMovementAddress;
import com.example.family.repository.HouseholdMovementAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository repository;

    @Transactional
    public void insertHouseholdMovementAddress(Household household, HouseholdMovementAddressForm form) {
        HouseholdMovementAddress.Pk pk =
                new HouseholdMovementAddress.Pk(form.getReportDate(), household.getSerialNumber());

        List<HouseholdMovementAddress> allByPkReportDateAndLastAddressTrue =
                repository.findAllByHouseholdAndLastAddress_Y(household);

        allByPkReportDateAndLastAddressTrue.forEach(movementAddress ->
                movementAddress.setLastAddress(HouseholdMovementAddress.LastAddress.N));

        HouseholdMovementAddress householdMovementAddress = HouseholdMovementAddress.builder()
                .pk(pk)
                .household(household)
                .address(form.getAddress())
                .lastAddress(HouseholdMovementAddress.LastAddress.Y)
                .build();

        repository.save(householdMovementAddress);
    }

    @Transactional(readOnly = true)
    public HouseholdMovementAddress getHouseholdMovementAddress(LocalDate reportDate, int householdSerialNumber) {
        return repository.findById(new HouseholdMovementAddress.Pk(reportDate, householdSerialNumber))
                .orElseThrow(() -> new IllegalArgumentException("일치하는 데이터가 없습니다."));
    }

    @Transactional
    public void modifyHouseholdMovementAddress(Household household, LocalDate reportDate,
                                               HouseholdMovementAddressEditForm form) {
        HouseholdMovementAddress householdMovementAddress =
                getHouseholdMovementAddress(reportDate, household.getSerialNumber());

        householdMovementAddress.setAddress(form.getAddress());
    }

    @Transactional
    public void deleteHouseholdMovementAddress(Household household, LocalDate reportDate) {
        HouseholdMovementAddress householdMovementAddress =
                getHouseholdMovementAddress(reportDate, household.getSerialNumber());
        repository.delete(householdMovementAddress);
    }
}
