package com.example.employmentdatasystem.mapper;

import com.example.employmentdatasystem.entity.EmploymentReport;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmploymentReportMapper {
    @Insert("INSERT INTO employment_report(enterprise_id,period_id,employment_count,unemployment_count,decrease_reason,submitter_id,city_audit_status) VALUES(#{enterpriseId},#{periodId},#{employmentCount},#{unemploymentCount},#{decreaseReason},#{submitterId},'PENDING')")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(EmploymentReport report);

    @Select("SELECT * FROM employment_report WHERE enterprise_id=#{enterpriseId} ORDER BY id DESC LIMIT 1")
    EmploymentReport findLatestByEnterprise(Long enterpriseId);

    @Select("SELECT * FROM employment_report WHERE submitter_id=#{userId} ORDER BY id DESC")
    List<EmploymentReport> findBySubmitter(Long userId);

    @Select("SELECT r.* FROM employment_report r JOIN enterprise_info e ON r.enterprise_id=e.id WHERE r.city_audit_status='PENDING' AND e.city_code=#{cityCode} ORDER BY r.id DESC")
    List<EmploymentReport> findCityPending(String cityCode);

    @Update("UPDATE employment_report SET city_audit_status=#{status}, city_audit_comment=#{comment}, city_auditor_id=#{auditorId}, city_audit_time=NOW() WHERE id=#{id}")
    int updateCityAudit(@Param("id") Long id, @Param("status") String status, @Param("comment") String comment, @Param("auditorId") Long auditorId);

    @Select("SELECT r.* FROM employment_report r WHERE r.city_audit_status='APPROVED' ORDER BY r.id DESC")
    List<EmploymentReport> findProvinceApproved();

    @Select("SELECT e.city_code cityCode, SUM(r.employment_count) employmentTotal, SUM(r.unemployment_count) unemploymentTotal FROM employment_report r JOIN enterprise_info e ON r.enterprise_id=e.id WHERE r.city_audit_status='APPROVED' GROUP BY e.city_code")
    List<Map<String,Object>> cityStats();

    @Select("SELECT COALESCE(SUM(employment_count),0) employmentTotal, COALESCE(SUM(unemployment_count),0) unemploymentTotal FROM employment_report WHERE city_audit_status='APPROVED'")
    Map<String,Object> provinceTotal();
}