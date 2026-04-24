package com.example.employmentdatasystem.service;

import com.example.employmentdatasystem.entity.User;

import java.util.List;

public interface UserService {
    List<User> listUsers(Long operatorId);
    User createUser(Long operatorId, User user);
    void updateUserStatus(Long operatorId, Long userId, String status);
    User getById(Long id);
}