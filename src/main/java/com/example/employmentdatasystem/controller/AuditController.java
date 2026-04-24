package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.common.ApiResponse;
import com.example.employmentdatasystem.entity.EmploymentReport;
import com.example.employmentdatasystem.entity.EnterpriseInfo;
import com.example.employmentdatasystem.service.AuditService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/city/pending-reports")
    public ApiResponse<List<EmploymentReport>> cityPending(@RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(auditService.cityPendingReports(userId));
    }

    @PostMapping("/city/report/{reportId}")
    public ApiResponse<Void> cityAudit(@RequestHeader("X-User-Id") Long userId,
                                       @PathVariable Long reportId,
                                       @RequestParam String status,
                                       @RequestParam(required = false) String comment) {
        auditService.cityAuditReport(userId, reportId, status, comment);
        return ApiResponse.success("市级审核完成", null);
    }

    @GetMapping("/province/pending-filings")
    public ApiResponse<List<EnterpriseInfo>> provincePending(@RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(auditService.provincePendingFilings(userId));
    }
}