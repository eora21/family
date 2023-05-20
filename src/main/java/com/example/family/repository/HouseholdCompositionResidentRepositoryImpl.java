package com.example.family.repository;

import com.example.family.domain.report.household.HouseholdCompositionResidentDto;
import com.example.family.domain.report.household.QHouseholdCompositionResidentDto;
import com.example.family.entity.HouseholdCompositionResident;
import com.example.family.entity.QHouseholdCompositionResident;
import com.example.family.entity.QResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport
        implements HouseholdCompositionResidentRepositoryCustom {

    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResident.class);
    }

    @Override
    public List<HouseholdCompositionResidentDto> getHouseholdCompositionResidents(int householdSerialNumber) {
        QHouseholdCompositionResident householdCompositionResident =
                QHouseholdCompositionResident.householdCompositionResident;
        QResident resident = QResident.resident;

        return from(householdCompositionResident)
                .innerJoin(resident)
                .on(householdCompositionResident.resident.eq(resident))
                .where(householdCompositionResident.household.serialNumber.eq(householdSerialNumber))
                .select(new QHouseholdCompositionResidentDto(
                        householdCompositionResident.relationshipCode, resident.name, resident.registrationNumber,
                        householdCompositionResident.reportDate,
                        householdCompositionResident.compositionChangeReasonCode))
                .fetch();
    }
}
