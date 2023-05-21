package com.example.family.domain.resident.repository;

import com.example.family.domain.household_composition_resident.entity.HouseholdCompositionResident;
import com.example.family.entity.QHousehold;
import com.example.family.entity.QHouseholdCompositionResident;
import com.example.family.entity.QResident;
import com.example.family.domain.resident.entity.Resident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {

    public ResidentRepositoryImpl() {
        super(Resident.class);
    }

    @Override
    public boolean hasFamily(int residentSerialNumber) {
        QResident resident = QResident.resident;
        QHousehold household = QHousehold.household;
        QHouseholdCompositionResident householdCompositionResident =
                QHouseholdCompositionResident.householdCompositionResident;

        int count = from(resident)
                .innerJoin(household)
                .on(resident.eq(household.resident))
                .innerJoin(householdCompositionResident)
                .on(
                        household.eq(householdCompositionResident.household)
                                .and(householdCompositionResident.relationshipCode.ne(
                                        HouseholdCompositionResident.RelationshipCode.본인)))
                .select(resident)
                .where(resident.serialNumber.eq(residentSerialNumber))
                .fetch().size();

        return count != 0;
    }
}
