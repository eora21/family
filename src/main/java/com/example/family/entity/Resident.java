package com.example.family.entity;

import com.example.family.domain.ResidentForm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer serialNumber;

    @Setter
    @Column
    String name;

    @Setter
    @Column(name = "resident_registration_number")
    String registrationNumber;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "gender_code")
    Gender gender;

    @Setter
    @Column(name = "birth_date")
    LocalDateTime birthDate;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "birth_place_code")
    BirthPlace birthPlaceCode;

    @Setter
    @Column(name = "registration_base_address")
    String registrationBaseAddress;

    @Setter
    @Column(name = "death_date")
    LocalDateTime deathDate;

    @Setter
    @Column(name = "death_place_code")
    String deathPlaceCode;

    @Setter
    @Column(name = "death_place_address")
    String deathPlaceAddress;

    public enum Gender {
        남, 여
    }

    public enum BirthPlace {
        자택, 병원, 기타
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