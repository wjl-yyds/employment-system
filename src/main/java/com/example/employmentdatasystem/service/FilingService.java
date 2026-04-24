package com.example.employmentdatasystem.service;

import com.example.employmentdatasystem.entity.EnterpriseInfo;

import java.util.List;

public interface FilingService {
    EnterpriseInfo submitFiling(Long userId, EnterpriseInfo info);
    void provinceAudit(Long userId, Long enterpriseId, String status, String comment);
    List<EnterpriseInfo> listPending(Long userId);
}