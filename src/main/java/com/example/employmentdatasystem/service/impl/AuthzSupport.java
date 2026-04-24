package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.exception.BizException;
import com.example.employmentdatasystem.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthzSupport {
    private final UserMapper userMapper;
    public AuthzSupport(UserMapper userMapper) { this.userMapper = userMapper; }

    public User requireUser(Long userId) {
        User user = userMapper.findById(userId);
        if (user == null || !"ACTIVE".equals(user.getStatus())) {
            throw new BizException(4010, "用户不存在或已停用");
        }
        return user;
    }

    public void requireRole(User user, String... roles) {
        for (String role : roles) {
            if (role.equals(user.getRoleCode())) {
                return;
            }
        }
        throw new BizException(4030, "无权限执行该操作");
    }
}