package com.example.employmentdatasystem.mapper;

import com.example.employmentdatasystem.entity.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {
    @Insert("INSERT INTO notice(title,content,target_role,publisher_id,status,publish_time) VALUES(#{title},#{content},#{targetRole},#{publisherId},'PUBLISHED',NOW())")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(Notice notice);

    @Select("SELECT * FROM notice WHERE target_role='ALL' OR target_role=#{roleCode} ORDER BY id DESC")
    List<Notice> listByRole(String roleCode);
}