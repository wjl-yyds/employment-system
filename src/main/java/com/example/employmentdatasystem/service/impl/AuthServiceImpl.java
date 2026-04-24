package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.exception.BizException;
import com.example.employmentdatasystem.mapper.UserMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;

    public AuthServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new BizException(4011, "用户名或密码错误");
        }
        if (!"ACTIVE".equals(user.getStatus())) {
            throw new BizException(4012, "账号已停用");
        }
        user.setPassword(null);
        return user;
    }

    @Override
    public User register(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new BizException(4009, "用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new BizException(4010, "密码不能为空");
        }
        if (user.getRealName() == null || user.getRealName().trim().isEmpty()) {
            throw new BizException(4011, "姓名不能为空");
        }
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new BizException(4012, "用户名已存在");
        }
        user.setRoleCode(RoleCodes.ENTERPRISE);
        user.setStatus("ACTIVE");
        userMapper.insert(user);
        user.setPassword(null);
        return user;
    }
}