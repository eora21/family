package com.example.family.repository;

import com.example.family.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
}
