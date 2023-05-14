package com.example.family.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Setter
@AllArgsConstructor
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private FamilyRelationshipPk pk;

    @MapsId("familyResidentId")
    @ManyToOne
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;

    @MapsId("baseResidentId")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;

    @Enumerated(EnumType.STRING)
    @Column(name = "family_relationship_code")
    Relationship code;

    public enum Relationship {
        부, 모, 배우자, 자녀
    }

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class FamilyRelationshipPk implements Serializable {
        private Integer familyResidentId;
        private Integer baseResidentId;
    }


}
