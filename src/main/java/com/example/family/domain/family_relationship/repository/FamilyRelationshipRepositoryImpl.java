package com.example.family.domain.family_relationship.repository;

import com.example.family.domain.family_relationship.entity.FamilyRelationship;
import com.example.family.domain.family_relationship.entity.QFamilyRelationship;
import com.example.family.domain.family_relationship.model.dto.FamilyRelationshipDto;
import com.example.family.domain.family_relationship.model.dto.QFamilyRelationshipDto;
import com.example.family.domain.resident.entity.QResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationshipRepositoryCustom {

    public FamilyRelationshipRepositoryImpl() {
        super(FamilyRelationship.class);
    }

    @Override
    public List<FamilyRelationshipDto> getMyFamilyRelationship(int residentSerialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;

        return from(familyRelationship)
                .innerJoin(resident)
                .on(familyRelationship.baseResident.eq(resident))
                .where(familyRelationship.familyResident.serialNumber.eq(residentSerialNumber))
                .select(new QFamilyRelationshipDto(
                        familyRelationship.familyRelationshipCode, resident.name, resident.birthDate,
                        resident.registrationNumber, resident.gender))
                .fetch();
    }
}
