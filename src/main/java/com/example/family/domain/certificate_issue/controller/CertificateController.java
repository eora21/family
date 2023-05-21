package com.example.family.domain.certificate_issue.controller;

import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import com.example.family.domain.certificate_issue.model.dto.CertificateDto;
import com.example.family.domain.certificate_issue.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {
    private final CertificateService certificateService;

    @GetMapping("log")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getTotalReport(@PageableDefault(size = 1) Pageable pageable, Model model) {
        model.addAttribute("logs", certificateService.getCertificateLog(pageable));
        return "certificateIssueLog";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public String getCertificateUserReport(Principal principal, Model model,
                                           @RequestParam CertificateIssue.CertificateType certificateType) {
        String loginId = principal.getName();
        CertificateDto certificateDto = certificateService.getCertificateDto(loginId, certificateType);
        model.addAttribute("certificate", certificateDto);
        return certificateDto.getCertificateType().toString();
    }

    @GetMapping("{residentSerialNumber}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getCertificateResidentReport(Model model, @PathVariable int residentSerialNumber,
                                           @RequestParam CertificateIssue.CertificateType certificateType) {
        CertificateDto certificateDto = certificateService.getCertificateDto(residentSerialNumber, certificateType);
        model.addAttribute("certificate", certificateDto);
        return certificateDto.getCertificateType().toString();
    }
}
