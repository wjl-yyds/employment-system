package com.example.employmentdatasystem.service;

import com.example.employmentdatasystem.entity.User;

public interface AuthService {
    User login(String username, String password);
    User register(User user);
}