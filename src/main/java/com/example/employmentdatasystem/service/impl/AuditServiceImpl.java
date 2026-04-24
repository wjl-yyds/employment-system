package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.EmploymentReport;
import com.example.employmentdatasystem.entity.EnterpriseInfo;
import com.example.employmentdatasystem.entity.ReportAuditRecord;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.exception.BizException;
import com.example.employmentdatasystem.mapper.EmploymentReportMapper;
import com.example.employmentdatasystem.mapper.EnterpriseInfoMapper;
import com.example.employmentdatasystem.mapper.ReportAuditRecordMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.AuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
    private final EmploymentReportMapper reportMapper;
    private final EnterpriseInfoMapper enterpriseInfoMapper;
    private final ReportAuditRecordMapper reportAuditRecordMapper;
    private final AuthzSupport authzSupport;

    public AuditServiceImpl(EmploymentReportMapper reportMapper, EnterpriseInfoMapper enterpriseInfoMapper, ReportAuditRecordMapper reportAuditRecordMapper, AuthzSupport authzSupport) {
        this.reportMapper = reportMapper;
        this.enterpriseInfoMapper = enterpriseInfoMapper;
        this.reportAuditRecordMapper = reportAuditRecordMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public List<EmploymentReport> cityPendingReports(Long userId) {
        User cityUser = authzSupport.requireUser(userId);
        authzSupport.requireRole(cityUser, RoleCodes.CITY, RoleCodes.ADMIN);
        return reportMapper.findCityPending(cityUser.getCityCode());
    }

    @Override
    public void cityAuditReport(Long userId, Long reportId, String status, String comment) {
        User cityUser = authzSupport.requireUser(userId);
        authzSupport.requireRole(cityUser, RoleCodes.CITY, RoleCodes.ADMIN);
        if (!"APPROVED".equals(status) && !"REJECTED".equals(status)) {
            throw new BizException(4013, "审核状态不合法");
        }
        reportMapper.updateCityAudit(reportId, status, comment, userId);
        ReportAuditRecord r = new ReportAuditRecord();
        r.setReportId(reportId);
        r.setAuditorId(userId);
        r.setAuditStatus(status);
        r.setAuditComment(comment);
        reportAuditRecordMapper.insert(r);
    }

    @Override
    public List<EnterpriseInfo> provincePendingFilings(Long userId) {
        User u = authzSupport.requireUser(userId);
        authzSupport.requireRole(u, RoleCodes.PROVINCE, RoleCodes.ADMIN);
        return enterpriseInfoMapper.findPending();
    }
}