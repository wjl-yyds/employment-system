package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.exception.BizException;
import com.example.employmentdatasystem.mapper.UserMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final AuthzSupport authzSupport;

    public UserServiceImpl(UserMapper userMapper, AuthzSupport authzSupport) {
        this.userMapper = userMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public List<User> listUsers(Long operatorId) {
        User op = authzSupport.requireUser(operatorId);
        authzSupport.requireRole(op, RoleCodes.ADMIN);
        List<User> users = userMapper.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    @Override
    public User createUser(Long operatorId, User user) {
        User op = authzSupport.requireUser(operatorId);
        authzSupport.requireRole(op, RoleCodes.ADMIN);
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new BizException(4003, "用户名已存在");
        }
        userMapper.insert(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public void updateUserStatus(Long operatorId, Long userId, String status) {
        User op = authzSupport.requireUser(operatorId);
        authzSupport.requireRole(op, RoleCodes.ADMIN);
        userMapper.updateStatus(userId, status);
    }

    @Override
    public User getById(Long id) {
        return userMapper.findById(id);
    }
}