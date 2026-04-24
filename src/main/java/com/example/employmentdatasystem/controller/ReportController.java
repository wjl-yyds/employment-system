package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.common.ApiResponse;
import com.example.employmentdatasystem.entity.EmploymentReport;
import com.example.employmentdatasystem.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/submit")
    public ApiResponse<EmploymentReport> submit(@RequestHeader("X-User-Id") Long userId, @RequestBody EmploymentReport report) {
        return ApiResponse.success("填报提交成功", reportService.submitReport(userId, report));
    }

    @GetMapping("/mine")
    public ApiResponse<List<EmploymentReport>> mine(@RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(reportService.myReports(userId));
    }
}