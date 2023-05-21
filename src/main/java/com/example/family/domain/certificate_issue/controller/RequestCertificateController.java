package com.example.family.domain.certificate_issue.controller;

import com.example.family.domain.certificate_issue.model.dto.CertificateDto;
import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import com.example.family.domain.certificate_issue.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class RequestCertificateController {
    private final CertificateService certificateService;

    @GetMapping("admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void getTotalReport(Pageable pageable) {

    }

    @GetMapping("user")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public String getCertificateUserReport(Principal principal, Model model,
                                           @RequestParam CertificateIssue.CertificateType certificateType) {
        String name = principal.getName();
        CertificateDto reportDto = certificateService.getReportDto(name, certificateType);
        model.addAttribute("report", reportDto);
        return reportDto.getCertificateType().toString();
    }
}
