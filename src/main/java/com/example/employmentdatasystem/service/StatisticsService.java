package com.example.employmentdatasystem.service;

import java.util.Map;

public interface StatisticsService {
    Map<String, Object> provinceSummary(Long userId);
}