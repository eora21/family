package com.example.family.domain.certificate_issue.service;

import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import com.example.family.domain.certificate_issue.model.dto.CertificateDto;
import com.example.family.domain.certificate_issue.model.dto.CertificateIssueDto;
import com.example.family.domain.certificate_issue.model.dto.Certification;
import com.example.family.domain.certificate_issue.repository.CertificateIssueRepository;
import com.example.family.domain.family_relationship.entity.FamilyRelationship;
import com.example.family.domain.family_relationship.model.dto.FamilyRelationshipCertification;
import com.example.family.domain.family_relationship.model.dto.FamilyRelationshipDto;
import com.example.family.domain.family_relationship.repository.FamilyRelationshipRepository;
import com.example.family.domain.household.entity.Household;
import com.example.family.domain.household.model.dto.HouseholdDto;
import com.example.family.domain.household.repository.HouseholdRepository;
import com.example.family.domain.household_composition_resident.entity.HouseholdCompositionResident;
import com.example.family.domain.household_composition_resident.model.dto.HouseholdCompositionResidentDto;
import com.example.family.domain.household_composition_resident.repository.HouseholdCompositionResidentRepository;
import com.example.family.domain.household_movement_address.model.dto.HouseholdMovementAddressDto;
import com.example.family.domain.household_movement_address.repository.HouseholdMovementAddressRepository;
import com.example.family.domain.report.entity.Report;
import com.example.family.domain.report.model.dto.BirthReportDto;
import com.example.family.domain.report.model.dto.DeathReportDto;
import com.example.family.domain.report.repository.ReportRepository;
import com.example.family.domain.resident.entity.Resident;
import com.example.family.domain.resident.repository.ResidentRepository;
import com.example.family.domain.user.entity.User;
import com.example.family.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ResidentRepository residentRepository;
    private final ReportRepository reportRepository;
    private final Map<CertificateIssue.CertificateType, Function<Resident, Certification>> certificates;

    public CertificateService(FamilyRelationshipRepository familyRelationshipRepository,
                              UserRepository userRepository, CertificateIssueRepository certificateIssueRepository,
                              HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                              HouseholdMovementAddressRepository householdMovementAddressRepository,
                              HouseholdRepository householdRepository, ResidentRepository residentRepository,
                              ReportRepository reportRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.userRepository = userRepository;
        this.certificateIssueRepository = certificateIssueRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
        this.reportRepository = reportRepository;
        certificates = new EnumMap<>(CertificateIssue.CertificateType.class);
        certificates.put(CertificateIssue.CertificateType.주민등록등본, this::getHouseholdReport);
        certificates.put(CertificateIssue.CertificateType.가족관계증명서, this::getFamilyRelationshipReport);
        certificates.put(CertificateIssue.CertificateType.출생신고서, this::getBirthReport);
        certificates.put(CertificateIssue.CertificateType.사망신고서, this::getDeathReport);
    }

    @Transactional
    public CertificateDto getCertificateDto(String loginId, CertificateIssue.CertificateType certificateType) {
        User user = userRepository.getReferenceById(loginId);
        Resident resident = user.getResident();
        return getCertificate(certificateType, resident);
    }

    @Transactional
    public CertificateDto getCertificateDto(int residentSerialNumber, CertificateIssue.CertificateType certificateType) {
        Resident resident = residentRepository.getReferenceById(residentSerialNumber);
        return getCertificate(certificateType, resident);
    }

    private CertificateDto getCertificate(CertificateIssue.CertificateType certificateType, Resident resident) {
        CertificateIssue certificateIssue = certificateIssueRepository.save(CertificateIssue.builder()
                .resident(resident)
                .certificateType(certificateType)
                .certificateIssueDate(LocalDate.now())
                .build());

        Certification certification = certificates.get(certificateType)
                .apply(resident);

        return new CertificateDto(
                certificateIssue.getConfirmationNumber(),
                certificateIssue.getCertificateIssueDate(),
                certificateIssue.getCertificateType(),
                certification
        );
    }

    private FamilyRelationshipCertification getFamilyRelationshipReport(Resident resident) {
        List<FamilyRelationshipDto> myFamilyRelationship =
                familyRelationshipRepository.getMyFamilyRelationship(resident.getSerialNumber());

        myFamilyRelationship.add(0, new FamilyRelationshipDto(
                FamilyRelationship.FamilyRelationshipCode.본인,
                resident.getName(), resident.getBirthDate(), resident.getRegistrationNumber(), resident.getGender()));

        return new FamilyRelationshipCertification(resident.getRegistrationBaseAddress(), myFamilyRelationship);
    }

    private HouseholdDto getHouseholdReport(Resident resident) {
        HouseholdCompositionResident composition =
                householdCompositionResidentRepository.getByPk_ResidentSerialNumber(resident.getSerialNumber());
        Household household = householdRepository.getHousehold(composition.getPk().getHouseholdSerialNumber());
        Resident householdResident = household.getResident();

        List<HouseholdMovementAddressDto> householdMovementAddresses =
                householdMovementAddressRepository.findAllByHouseholdOrderByPk_reportDateDesc(household);

        List<HouseholdCompositionResidentDto> householdCompositionResidents =
                householdCompositionResidentRepository.getHouseholdCompositionResidents(
                        household.getSerialNumber());

        return new HouseholdDto(householdResident.getName(), household.getCompositionReasonCode(),
                household.getCompositionDate(), householdMovementAddresses, householdCompositionResidents);
    }

    private BirthReportDto getBirthReport(Resident resident) {
        Report birthReport = reportRepository.getBirthReport(resident);
        Resident reportResident = birthReport.getReportResident();
        List<FamilyRelationshipDto> myFamilyRelationship =
                familyRelationshipRepository.getMyFamilyRelationship(resident.getSerialNumber());

        FamilyRelationshipDto father = myFamilyRelationship.stream()
                .filter(relationship ->
                        relationship.getFamilyRelationshipCode() == FamilyRelationship.FamilyRelationshipCode.부)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("아버지와의 연관관계가 설정되어 있지 않습니다."));

        FamilyRelationshipDto mother = myFamilyRelationship.stream()
                .filter(relationship ->
                        relationship.getFamilyRelationshipCode() == FamilyRelationship.FamilyRelationshipCode.모)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("어머니와의 연관관계가 설정되어 있지 않습니다."));

        return new BirthReportDto(birthReport.getReportDate(), resident.getName(), resident.getGender(),
                resident.getBirthDate(), resident.getBirthPlaceCode(), resident.getRegistrationBaseAddress(),
                father.getName(), father.getResidentRegistrationNumber(), mother.getName(),
                mother.getResidentRegistrationNumber(), reportResident.getName(),
                reportResident.getRegistrationNumber(), birthReport.getBirthReportQualificationsCode(),
                birthReport.getEmailAddress(), birthReport.getPhoneNumber());
    }

    private DeathReportDto getDeathReport(Resident resident) {
        Report deathReport = reportRepository.getDeathReport(resident);
        Resident reportResident = deathReport.getReportResident();

        return new DeathReportDto(deathReport.getReportDate(), resident.getName(), resident.getRegistrationNumber(),
                resident.getDeathDate(), resident.getDeathPlaceCode(), resident.getDeathPlaceAddress(),
                reportResident.getName(), reportResident.getRegistrationNumber(),
                deathReport.getDeathReportQualificationsCode(), deathReport.getEmailAddress(),
                deathReport.getPhoneNumber());
    }

    public Page<CertificateIssueDto> getCertificateLog(Pageable pageable) {
        return certificateIssueRepository.findAll(pageable).map(CertificateIssueDto::newInstance);
    }
}
