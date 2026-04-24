package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.common.ApiResponse;
import com.example.employmentdatasystem.entity.SurveyPeriodConfig;
import com.example.employmentdatasystem.service.SurveyPeriodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey-periods")
public class SurveyPeriodController {
    private final SurveyPeriodService surveyPeriodService;

    public SurveyPeriodController(SurveyPeriodService surveyPeriodService) {
        this.surveyPeriodService = surveyPeriodService;
    }

    @PostMapping
    public ApiResponse<SurveyPeriodConfig> create(@RequestHeader("X-User-Id") Long userId,
                                                   @RequestBody SurveyPeriodConfig config) {
        return ApiResponse.success("调查期创建成功", surveyPeriodService.createPeriod(userId, config));
    }

    @PostMapping("/{periodId}/activate")
    public ApiResponse<Void> activate(@RequestHeader("X-User-Id") Long userId, @PathVariable Long periodId) {
        surveyPeriodService.activatePeriod(userId, periodId);
        return ApiResponse.success("调查期已启用", null);
    }

    @GetMapping
    public ApiResponse<List<SurveyPeriodConfig>> list(@RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(surveyPeriodService.listPeriods(userId));
    }
}