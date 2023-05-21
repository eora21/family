package com.example.family.domain.certificate_issue.repository;

import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import com.example.family.domain.certificate_issue.model.dto.CertificateIssueDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
}
