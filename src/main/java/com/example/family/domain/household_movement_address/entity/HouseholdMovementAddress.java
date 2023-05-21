package com.example.family.domain.household_movement_address.entity;

import com.example.family.domain.household.entity.Household;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "household_movement_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class HouseholdMovementAddress {
    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number")
    private Household household;

    @Setter
    @Column(name = "house_movement_address")
    private String address;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "last_address_yn")
    private LastAddress lastAddress;

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "house_movement_report_date")
        private LocalDate reportDate;

        private Integer householdSerialNumber;
    }

    public enum LastAddress {
        Y, N
    }
}
