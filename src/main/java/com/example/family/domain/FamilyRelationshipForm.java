package com.example.family.domain;

import com.example.family.entity.FamilyRelationship;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FamilyRelationshipForm {
    private int familySerialNumber;
    private FamilyRelationship.Relationship relationship;
}
