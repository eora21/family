package com.example.family.service;

import com.example.family.domain.report.CertificateDto;
import com.example.family.domain.report.ReportDto;
import com.example.family.domain.report.family.FamilyRelationshipDto;
import com.example.family.domain.report.family.FamilyRelationshipReportDto;
import com.example.family.domain.report.household.HouseholdCompositionResidentDto;
import com.example.family.domain.report.household.HouseholdDto;
import com.example.family.domain.report.household.HouseholdMovementAddressDto;
import com.example.family.entity.CertificateIssue;
import com.example.family.entity.Household;
import com.example.family.entity.HouseholdCompositionResident;
import com.example.family.entity.Resident;
import com.example.family.entity.User;
import com.example.family.repository.CertificateIssueRepository;
import com.example.family.repository.FamilyRelationshipRepository;
import com.example.family.repository.HouseholdCompositionResidentRepository;
import com.example.family.repository.HouseholdMovementAddressRepository;
import com.example.family.repository.HouseholdRepository;
import com.example.family.repository.ResidentRepository;
import com.example.family.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class CertificateService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final UserRepository userRepository;
    private final CertificateIssueRepository certificateIssueRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;
    private final Map<CertificateIssue.CertificateType, Function<Resident, ReportDto>> certificates;

    public CertificateService(FamilyRelationshipRepository familyRelationshipRepository,
                              UserRepository userRepository, CertificateIssueRepository certificateIssueRepository,
                              HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                              HouseholdMovementAddressRepository householdMovementAddressRepository,
                              HouseholdRepository householdRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.userRepository = userRepository;
        this.certificateIssueRepository = certificateIssueRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdRepository = householdRepository;
        certificates = new EnumMap<>(CertificateIssue.CertificateType.class);
        certificates.put(CertificateIssue.CertificateType.주민등록등본, this::getFamilyRelationshipReport);
        certificates.put(CertificateIssue.CertificateType.가족관계증명서, this::getHouseholdReport);
    }

    @Transactional
    public CertificateDto getReportDto(String userName, CertificateIssue.CertificateType certificateType) {
        User user = userRepository.getReferenceById(userName);
        Resident resident = user.getResident();

        CertificateIssue certificateIssue = certificateIssueRepository.save(CertificateIssue.builder()
                .resident(resident)
                .certificateType(certificateType)
                .certificateIssueDate(LocalDate.now())
                .build());

        ReportDto reportDto = certificates.get(certificateType)
                .apply(resident);

        return new CertificateDto(
                certificateIssue.getConfirmationNumber(),
                certificateIssue.getCertificateIssueDate(),
                certificateIssue.getCertificateType(),
                reportDto
        );
    }

    public FamilyRelationshipReportDto getFamilyRelationshipReport(Resident resident) {
        List<FamilyRelationshipDto> myFamilyRelationship =
                familyRelationshipRepository.getMyFamilyRelationship(resident.getSerialNumber());

        return new FamilyRelationshipReportDto(resident.getRegistrationBaseAddress(), myFamilyRelationship);
    }

    public HouseholdDto getHouseholdReport(Resident resident) {
        HouseholdCompositionResident composition =
                householdCompositionResidentRepository.getByPk_ResidentSerialNumber(resident.getSerialNumber());
        Household household = householdRepository.getReferenceById(composition.getPk().getHouseholdSerialNumber());
        Resident householdResident = household.getResident();

        List<HouseholdMovementAddressDto> householdMovementAddresses =
                householdMovementAddressRepository.findAllByHousehold(household);

        List<HouseholdCompositionResidentDto> householdCompositionResidents =
                householdCompositionResidentRepository.getHouseholdCompositionResidents(
                        household.getSerialNumber());

        return new HouseholdDto(householdResident.getName(), household.getCompositionReasonCode(),
                householdMovementAddresses, householdCompositionResidents);
    }
}
