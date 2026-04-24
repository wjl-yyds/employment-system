package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.EmploymentReport;
import com.example.employmentdatasystem.entity.EnterpriseInfo;
import com.example.employmentdatasystem.entity.SurveyPeriodConfig;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.exception.BizException;
import com.example.employmentdatasystem.mapper.EmploymentReportMapper;
import com.example.employmentdatasystem.mapper.EnterpriseInfoMapper;
import com.example.employmentdatasystem.mapper.SurveyPeriodMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final EmploymentReportMapper reportMapper;
    private final SurveyPeriodMapper surveyPeriodMapper;
    private final EnterpriseInfoMapper enterpriseInfoMapper;
    private final AuthzSupport authzSupport;

    public ReportServiceImpl(EmploymentReportMapper reportMapper, SurveyPeriodMapper surveyPeriodMapper, EnterpriseInfoMapper enterpriseInfoMapper, AuthzSupport authzSupport) {
        this.reportMapper = reportMapper;
        this.surveyPeriodMapper = surveyPeriodMapper;
        this.enterpriseInfoMapper = enterpriseInfoMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public EmploymentReport submitReport(Long userId, EmploymentReport report) {
        User user = authzSupport.requireUser(userId);
        authzSupport.requireRole(user, RoleCodes.ENTERPRISE);

        EnterpriseInfo enterprise = enterpriseInfoMapper.findById(report.getEnterpriseId());
        if (enterprise == null || !"APPROVED".equals(enterprise.getFilingStatus())) {
            throw new BizException(4006, "企业备案未通过，无法填报");
        }

        SurveyPeriodConfig active = surveyPeriodMapper.findActiveByDate(LocalDate.now());
        if (active == null) {
            throw new BizException(4007, "当前不在调查期内，禁止提交");
        }
        report.setPeriodId(active.getId());

        EmploymentReport last = reportMapper.findLatestByEnterprise(report.getEnterpriseId());
        if (last != null && report.getEmploymentCount() < last.getEmploymentCount()) {
            if (report.getDecreaseReason() == null || report.getDecreaseReason().trim().isEmpty()) {
                throw new BizException(4008, "就业人数减少时必须填写原因");
            }
        }

        report.setSubmitterId(userId);
        reportMapper.insert(report);
        return report;
    }

    @Override
    public List<EmploymentReport> myReports(Long userId) {
        authzSupport.requireUser(userId);
        return reportMapper.findBySubmitter(userId);
    }
}