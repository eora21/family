package com.example.family.domain.household.entity;

import com.example.family.domain.household_movement_address.entity.HouseholdMovementAddress;
import com.example.family.domain.resident.entity.Resident;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "household_composition_reason_code")
    private CompositionReasonCode compositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "household", cascade = CascadeType.REMOVE)
    private List<HouseholdMovementAddress> householdMovementAddress;

    public enum CompositionReasonCode {
        세대분리,
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
