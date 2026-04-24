package com.example.employmentdatasystem.service;

import javax.servlet.http.HttpServletResponse;

public interface ExportService {
    void exportApprovedReports(Long userId, HttpServletResponse response);
}