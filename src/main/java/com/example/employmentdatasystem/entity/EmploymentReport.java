package com.example.employmentdatasystem.entity;

import java.time.LocalDateTime;

public class EmploymentReport {
    private Long id;
    private Long enterpriseId;
    private Long periodId;
    private Integer employmentCount;
    private Integer unemploymentCount;
    private String decreaseReason;
    private Long submitterId;
    private String cityAuditStatus;
    private String cityAuditComment;
    private Long cityAuditorId;
    private LocalDateTime cityAuditTime;
    private LocalDateTime createdAt;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getEnterpriseId(){return enterpriseId;} public void setEnterpriseId(Long enterpriseId){this.enterpriseId=enterpriseId;}
    public Long getPeriodId(){return periodId;} public void setPeriodId(Long periodId){this.periodId=periodId;}
    public Integer getEmploymentCount(){return employmentCount;} public void setEmploymentCount(Integer employmentCount){this.employmentCount=employmentCount;}
    public Integer getUnemploymentCount(){return unemploymentCount;} public void setUnemploymentCount(Integer unemploymentCount){this.unemploymentCount=unemploymentCount;}
    public String getDecreaseReason(){return decreaseReason;} public void setDecreaseReason(String decreaseReason){this.decreaseReason=decreaseReason;}
    public Long getSubmitterId(){return submitterId;} public void setSubmitterId(Long submitterId){this.submitterId=submitterId;}
    public String getCityAuditStatus(){return cityAuditStatus;} public void setCityAuditStatus(String cityAuditStatus){this.cityAuditStatus=cityAuditStatus;}
    public String getCityAuditComment(){return cityAuditComment;} public void setCityAuditComment(String cityAuditComment){this.cityAuditComment=cityAuditComment;}
    public Long getCityAuditorId(){return cityAuditorId;} public void setCityAuditorId(Long cityAuditorId){this.cityAuditorId=cityAuditorId;}
    public LocalDateTime getCityAuditTime(){return cityAuditTime;} public void setCityAuditTime(LocalDateTime cityAuditTime){this.cityAuditTime=cityAuditTime;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}