package com.example.family.domain.certificate_issue.repository;

import com.example.family.domain.certificate_issue.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
}
