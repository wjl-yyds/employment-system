package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.service.ExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/export")
public class ExportController {
    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/reports")
    public void export(@RequestHeader("X-User-Id") Long userId, HttpServletResponse response) {
        exportService.exportApprovedReports(userId, response);
    }
}