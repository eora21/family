package com.example.family.repository;

import com.example.family.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository
        extends JpaRepository<FamilyRelationship, FamilyRelationship.FamilyRelationshipPk> {

}
