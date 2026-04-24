package com.example.employmentdatasystem.mapper;

import com.example.employmentdatasystem.entity.FilingAuditRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilingAuditRecordMapper {
    @Insert("INSERT INTO filing_audit_record(enterprise_id,auditor_id,audit_status,audit_comment,audit_time) VALUES(#{enterpriseId},#{auditorId},#{auditStatus},#{auditComment},NOW())")
    int insert(FilingAuditRecord record);
}