package com.example.family.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialNumber;

    @Setter
    @Column
    private String name;

    @Setter
    @Column(name = "resident_registration_number")
    private String registrationNumber;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "gender_code")
    private Gender gender;

    @Setter
    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_place_code")
    private BirthPlace birthPlaceCode;

    @Setter
    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @Setter
    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "death_place_code")
    private DeathPlace deathPlaceCode;

    @Setter
    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "familyResident", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> familyRelationships;

    @OneToMany(mappedBy = "baseResident", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> baseRelationships;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<Report> reports;

    @OneToMany(mappedBy = "reportResident", cascade = CascadeType.REMOVE)
    private List<Report> myReports;

    public enum Gender {
        남, 여
    }

    public enum BirthPlace {
        자택, 병원, 기타
    }

    public enum DeathPlace {
        주택, 의료기관, 사회복지시설, 산업장, 공공시설, 도로, 상업서비스시설, 농장, 병원이송중사망, 기타
    }


    @Builder
    private Resident(String name, String registrationNumber, Gender gender, LocalDateTime birthDate,
                     BirthPlace birthPlaceCode, String registrationBaseAddress) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
    }

}