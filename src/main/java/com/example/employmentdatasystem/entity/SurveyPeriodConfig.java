package com.example.employmentdatasystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SurveyPeriodConfig {
    private Long id;
    private String periodName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long createdBy;
    private LocalDateTime createdAt;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getPeriodName(){return periodName;} public void setPeriodName(String periodName){this.periodName=periodName;}
    public LocalDate getStartDate(){return startDate;} public void setStartDate(LocalDate startDate){this.startDate=startDate;}
    public LocalDate getEndDate(){return endDate;} public void setEndDate(LocalDate endDate){this.endDate=endDate;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public Long getCreatedBy(){return createdBy;} public void setCreatedBy(Long createdBy){this.createdBy=createdBy;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;}
}