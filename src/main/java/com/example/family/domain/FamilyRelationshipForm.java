package com.example.family.domain;

import com.example.family.entity.FamilyRelationship;
import lombok.Getter;

@Getter
public class FamilyRelationshipForm {
    private int familySerialNumber;
    private FamilyRelationship.Relationship relationship;
}
