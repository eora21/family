package com.example.family.domain.family_relationship.model.form;

import com.example.family.domain.family_relationship.entity.FamilyRelationship;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FamilyRelationshipForm {
    private int familySerialNumber;
    private FamilyRelationship.FamilyRelationshipCode familyRelationshipCode;
}
