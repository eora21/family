package com.example.family.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "users")
public class User {
    @Id
    @Column(name = "login_id")
    private String loginId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    public enum Role {
        ROLE_ADMIN, ROLE_USER, ROLE_GUEST;
    }
}
