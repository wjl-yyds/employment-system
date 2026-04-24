package com.example.employmentdatasystem.service;

import com.example.employmentdatasystem.entity.SurveyPeriodConfig;

import java.util.List;

public interface SurveyPeriodService {
    SurveyPeriodConfig createPeriod(Long userId, SurveyPeriodConfig config);
    void activatePeriod(Long userId, Long periodId);
    List<SurveyPeriodConfig> listPeriods(Long userId);
}