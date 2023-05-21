package com.example.family.domain.resident.repository;

import com.example.family.domain.resident.model.dto.ResidentDto;
import com.example.family.domain.resident.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Integer>, ResidentRepositoryCustom {
    Page<ResidentDto> findAllDtoBy(Pageable pageable);
}
