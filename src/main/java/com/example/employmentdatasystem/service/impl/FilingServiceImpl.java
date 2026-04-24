package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.EnterpriseInfo;
import com.example.employmentdatasystem.entity.FilingAuditRecord;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.exception.BizException;
import com.example.employmentdatasystem.mapper.EnterpriseInfoMapper;
import com.example.employmentdatasystem.mapper.FilingAuditRecordMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.FilingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilingServiceImpl implements FilingService {
    private final EnterpriseInfoMapper enterpriseInfoMapper;
    private final FilingAuditRecordMapper filingAuditRecordMapper;
    private final AuthzSupport authzSupport;

    public FilingServiceImpl(EnterpriseInfoMapper enterpriseInfoMapper, FilingAuditRecordMapper filingAuditRecordMapper, AuthzSupport authzSupport) {
        this.enterpriseInfoMapper = enterpriseInfoMapper;
        this.filingAuditRecordMapper = filingAuditRecordMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public EnterpriseInfo submitFiling(Long userId, EnterpriseInfo info) {
        User user = authzSupport.requireUser(userId);
        authzSupport.requireRole(user, RoleCodes.ENTERPRISE);
        if (enterpriseInfoMapper.findBySocialCreditCode(info.getSocialCreditCode()) != null) {
            throw new BizException(4004, "统一社会信用代码已存在");
        }
        info.setCreatedBy(userId);
        enterpriseInfoMapper.insert(info);
        return info;
    }

    @Override
    public void provinceAudit(Long userId, Long enterpriseId, String status, String comment) {
        User user = authzSupport.requireUser(userId);
        authzSupport.requireRole(user, RoleCodes.PROVINCE, RoleCodes.ADMIN);
        if (!"APPROVED".equals(status) && !"REJECTED".equals(status)) {
            throw new BizException(4005, "审核状态不合法");
        }
        enterpriseInfoMapper.updateFilingStatus(enterpriseId, status, comment);
        FilingAuditRecord record = new FilingAuditRecord();
        record.setEnterpriseId(enterpriseId);
        record.setAuditorId(userId);
        record.setAuditStatus(status);
        record.setAuditComment(comment);
        filingAuditRecordMapper.insert(record);
    }

    @Override
    public List<EnterpriseInfo> listPending(Long userId) {
        User user = authzSupport.requireUser(userId);
        authzSupport.requireRole(user, RoleCodes.PROVINCE, RoleCodes.ADMIN);
        return enterpriseInfoMapper.findPending();
    }
}