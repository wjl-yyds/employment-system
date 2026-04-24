package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.common.ApiResponse;
import com.example.employmentdatasystem.entity.EnterpriseInfo;
import com.example.employmentdatasystem.service.FilingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filings")
public class FilingController {
    private final FilingService filingService;

    public FilingController(FilingService filingService) {
        this.filingService = filingService;
    }

    @PostMapping("/submit")
    public ApiResponse<EnterpriseInfo> submit(@RequestHeader("X-User-Id") Long userId, @RequestBody EnterpriseInfo info) {
        return ApiResponse.success("备案提交成功", filingService.submitFiling(userId, info));
    }

    @PostMapping("/{enterpriseId}/province-audit")
    public ApiResponse<Void> provinceAudit(@RequestHeader("X-User-Id") Long userId,
                                           @PathVariable Long enterpriseId,
                                           @RequestParam String status,
                                           @RequestParam(required = false) String comment) {
        filingService.provinceAudit(userId, enterpriseId, status, comment);
        return ApiResponse.success("备案审核完成", null);
    }

    @GetMapping("/pending")
    public ApiResponse<List<EnterpriseInfo>> pending(@RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(filingService.listPending(userId));
    }
}