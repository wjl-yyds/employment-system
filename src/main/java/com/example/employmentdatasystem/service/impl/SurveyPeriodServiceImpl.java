package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.SurveyPeriodConfig;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.mapper.SurveyPeriodMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.SurveyPeriodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyPeriodServiceImpl implements SurveyPeriodService {
    private final SurveyPeriodMapper surveyPeriodMapper;
    private final AuthzSupport authzSupport;

    public SurveyPeriodServiceImpl(SurveyPeriodMapper surveyPeriodMapper, AuthzSupport authzSupport) {
        this.surveyPeriodMapper = surveyPeriodMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public SurveyPeriodConfig createPeriod(Long userId, SurveyPeriodConfig config) {
        User u = authzSupport.requireUser(userId);
        authzSupport.requireRole(u, RoleCodes.ADMIN, RoleCodes.PROVINCE);
        config.setCreatedBy(userId);
        if (config.getStatus() == null) {
            config.setStatus("INACTIVE");
        }
        surveyPeriodMapper.insert(config);
        return config;
    }

    @Override
    public void activatePeriod(Long userId, Long periodId) {
        User u = authzSupport.requireUser(userId);
        authzSupport.requireRole(u, RoleCodes.ADMIN, RoleCodes.PROVINCE);
        surveyPeriodMapper.deactivateAll();
        surveyPeriodMapper.activate(periodId);
    }

    @Override
    public List<SurveyPeriodConfig> listPeriods(Long userId) {
        authzSupport.requireUser(userId);
        return surveyPeriodMapper.findAll();
    }
}