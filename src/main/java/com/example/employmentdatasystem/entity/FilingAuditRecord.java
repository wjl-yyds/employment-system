package com.example.employmentdatasystem.entity;

import java.time.LocalDateTime;

public class FilingAuditRecord {
    private Long id;
    private Long enterpriseId;
    private Long auditorId;
    private String auditStatus;
    private String auditComment;
    private LocalDateTime auditTime;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getEnterpriseId(){return enterpriseId;} public void setEnterpriseId(Long enterpriseId){this.enterpriseId=enterpriseId;}
    public Long getAuditorId(){return auditorId;} public void setAuditorId(Long auditorId){this.auditorId=auditorId;}
    public String getAuditStatus(){return auditStatus;} public void setAuditStatus(String auditStatus){this.auditStatus=auditStatus;}
    public String getAuditComment(){return auditComment;} public void setAuditComment(String auditComment){this.auditComment=auditComment;}
    public LocalDateTime getAuditTime(){return auditTime;} public void setAuditTime(LocalDateTime auditTime){this.auditTime=auditTime;}
}