package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.mapper.EmploymentReportMapper;
import com.example.employmentdatasystem.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final EmploymentReportMapper reportMapper;
    private final AuthzSupport authzSupport;

    public StatisticsServiceImpl(EmploymentReportMapper reportMapper, AuthzSupport authzSupport) {
        this.reportMapper = reportMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public Map<String, Object> provinceSummary(Long userId) {
        User u = authzSupport.requireUser(userId);
        authzSupport.requireRole(u, RoleCodes.PROVINCE, RoleCodes.ADMIN);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", reportMapper.provinceTotal());
        result.put("cityStats", reportMapper.cityStats());
        return result;
    }
}