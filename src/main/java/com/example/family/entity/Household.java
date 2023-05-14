package com.example.family.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "household")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number")
    private Integer serialNumber;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident resident;

    @Column(name = "household_composition_date")
    private LocalDate compositionDate;

    @Column(name = "household_composition_reason_code")
    private CompositionReasonCode compositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentAddress;

    public enum CompositionReasonCode {
        세대분리
    }

    @Builder
    private Household(Resident resident, LocalDate compositionDate, CompositionReasonCode compositionReasonCode,
                      String currentAddress) {
        this.resident = resident;
        this.compositionDate = compositionDate;
        this.compositionReasonCode = compositionReasonCode;
        this.currentAddress = currentAddress;
    }
}
