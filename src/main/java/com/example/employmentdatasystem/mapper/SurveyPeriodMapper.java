package com.example.employmentdatasystem.mapper;

import com.example.employmentdatasystem.entity.SurveyPeriodConfig;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SurveyPeriodMapper {
    @Insert("INSERT INTO survey_period_config(period_name,start_date,end_date,status,created_by) VALUES(#{periodName},#{startDate},#{endDate},#{status},#{createdBy})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(SurveyPeriodConfig config);

    @Select("SELECT * FROM survey_period_config WHERE status='ACTIVE' LIMIT 1")
    SurveyPeriodConfig findActive();

    @Select("SELECT * FROM survey_period_config WHERE id=#{id}")
    SurveyPeriodConfig findById(Long id);

    @Select("SELECT * FROM survey_period_config ORDER BY id DESC")
    List<SurveyPeriodConfig> findAll();

    @Update("UPDATE survey_period_config SET status='INACTIVE'")
    int deactivateAll();

    @Update("UPDATE survey_period_config SET status='ACTIVE' WHERE id=#{id}")
    int activate(Long id);

    @Select("SELECT * FROM survey_period_config WHERE start_date <= #{today} AND end_date >= #{today} AND status='ACTIVE' LIMIT 1")
    SurveyPeriodConfig findActiveByDate(LocalDate today);
}