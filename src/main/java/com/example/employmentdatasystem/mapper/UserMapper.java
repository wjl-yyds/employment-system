package com.example.employmentdatasystem.mapper;

import com.example.employmentdatasystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM sys_user WHERE username=#{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM sys_user WHERE id=#{id}")
    User findById(Long id);

    @Select("SELECT * FROM sys_user ORDER BY id DESC")
    List<User> findAll();

    @Insert("INSERT INTO sys_user(username,password,real_name,role_code,city_code,enterprise_id,status) " +
            "VALUES(#{username},#{password},#{realName},#{roleCode},#{cityCode,jdbcType=VARCHAR},#{enterpriseId,jdbcType=BIGINT},'ACTIVE')")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);

    @Update("UPDATE sys_user SET status=#{status} WHERE id=#{id}")
    int updateStatus(@Param("id") Long id, @Param("status") String status);
}
