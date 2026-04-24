package com.example.employmentdatasystem.entity;

import java.time.LocalDateTime;

public class EnterpriseInfo {
    private Long id;
    private String enterpriseName;
    private String socialCreditCode;
    private String cityCode;
    private String contactPerson;
    private String contactPhone;
    private String filingStatus;
    private String filingReason;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getEnterpriseName(){return enterpriseName;} public void setEnterpriseName(String enterpriseName){this.enterpriseName=enterpriseName;}
    public String getSocialCreditCode(){return socialCreditCode;} public void setSocialCreditCode(String socialCreditCode){this.socialCreditCode=socialCreditCode;}
    public String getCityCode(){return cityCode;} public void setCityCode(String cityCode){this.cityCode=cityCode;}
    public String getContactPerson(){return contactPerson;} public void setContactPerson(String contactPerson){this.contactPerson=contactPerson;}
    public String getContactPhone(){return contactPhone;} public void setContactPhone(String contactPhone){this.contactPhone=contactPhone;}
    public String getFilingStatus(){return filingStatus;} public void setFilingStatus(String filingStatus){this.filingStatus=filingStatus;}
    public String getFilingReason(){return filingReason;} public void setFilingReason(String filingReason){this.filingReason=filingReason;}
    public Long getCreatedBy(){return createdBy;} public void setCreatedBy(Long createdBy){this.createdBy=createdBy;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime updatedAt){this.updatedAt=updatedAt;}
}