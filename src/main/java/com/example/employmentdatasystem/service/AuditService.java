package com.example.employmentdatasystem.service;

import com.example.employmentdatasystem.entity.EmploymentReport;
import com.example.employmentdatasystem.entity.EnterpriseInfo;

import java.util.List;

public interface AuditService {
    List<EmploymentReport> cityPendingReports(Long userId);
    void cityAuditReport(Long userId, Long reportId, String status, String comment);
    List<EnterpriseInfo> provincePendingFilings(Long userId);
}