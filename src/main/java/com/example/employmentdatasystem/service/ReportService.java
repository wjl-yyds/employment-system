package com.example.employmentdatasystem.service;

import com.example.employmentdatasystem.entity.EmploymentReport;

import java.util.List;

public interface ReportService {
    EmploymentReport submitReport(Long userId, EmploymentReport report);
    List<EmploymentReport> myReports(Long userId);
}