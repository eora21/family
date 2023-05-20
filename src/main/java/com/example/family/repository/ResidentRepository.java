package com.example.family.repository;

import com.example.family.domain.ResidentDto;
import com.example.family.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Integer>, ResidentRepositoryCustom {
    List<ResidentDto> findAllDtoBy();
}
