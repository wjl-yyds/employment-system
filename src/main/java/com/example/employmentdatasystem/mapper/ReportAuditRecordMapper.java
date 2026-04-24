package com.example.employmentdatasystem.mapper;

import com.example.employmentdatasystem.entity.ReportAuditRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportAuditRecordMapper {
    @Insert("INSERT INTO report_audit_record(report_id,auditor_id,audit_status,audit_comment,audit_time) VALUES(#{reportId},#{auditorId},#{auditStatus},#{auditComment},NOW())")
    int insert(ReportAuditRecord record);
}