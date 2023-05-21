package com.example.family.domain.family_relationship.repository;

import com.example.family.domain.family_relationship.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository
        extends JpaRepository<FamilyRelationship, FamilyRelationship.FamilyRelationshipPk>, FamilyRelationshipRepositoryCustom {
}
