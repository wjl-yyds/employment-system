package com.example.employmentdatasystem.mapper;

import com.example.employmentdatasystem.entity.EnterpriseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnterpriseInfoMapper {
    @Insert("INSERT INTO enterprise_info(enterprise_name,social_credit_code,city_code,contact_person,contact_phone,filing_status,created_by) VALUES(#{enterpriseName},#{socialCreditCode},#{cityCode},#{contactPerson},#{contactPhone},'PENDING',#{createdBy})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(EnterpriseInfo info);

    @Select("SELECT * FROM enterprise_info WHERE id=#{id}")
    EnterpriseInfo findById(Long id);

    @Select("SELECT * FROM enterprise_info WHERE social_credit_code=#{code}")
    EnterpriseInfo findBySocialCreditCode(String code);

    @Select("SELECT * FROM enterprise_info WHERE filing_status='PENDING' ORDER BY id DESC")
    List<EnterpriseInfo> findPending();

    @Update("UPDATE enterprise_info SET filing_status=#{status}, filing_reason=#{reason} WHERE id=#{id}")
    int updateFilingStatus(@Param("id") Long id, @Param("status") String status, @Param("reason") String reason);
}