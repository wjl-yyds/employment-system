package com.example.employmentdatasystem.entity;

import java.time.LocalDateTime;

public class Notice {
    private Long id;
    private String title;
    private String content;
    private String targetRole;
    private Long publisherId;
    private LocalDateTime publishTime;
    private String status;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getContent(){return content;} public void setContent(String content){this.content=content;}
    public String getTargetRole(){return targetRole;} public void setTargetRole(String targetRole){this.targetRole=targetRole;}
    public Long getPublisherId(){return publisherId;} public void setPublisherId(Long publisherId){this.publisherId=publisherId;}
    public LocalDateTime getPublishTime(){return publishTime;} public void setPublishTime(LocalDateTime publishTime){this.publishTime=publishTime;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}