package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.common.ApiResponse;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestParam @NotBlank String username,
                                                   @RequestParam @NotBlank String password) {
        User user = authService.login(username, password);
        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getId());
        data.put("username", user.getUsername());
        data.put("realName", user.getRealName());
        data.put("roleCode", user.getRoleCode());
        data.put("cityCode", user.getCityCode());
        return ApiResponse.success("登录成功", data);
    }

    @PostMapping("/register")
    public ApiResponse<Map<String, Object>> register(@RequestBody User user) {
        User created = authService.register(user);
        Map<String, Object> data = new HashMap<>();
        data.put("userId", created.getId());
        data.put("username", created.getUsername());
        data.put("realName", created.getRealName());
        data.put("roleCode", created.getRoleCode());
        data.put("cityCode", created.getCityCode());
        return ApiResponse.success("注册成功", data);
    }
}