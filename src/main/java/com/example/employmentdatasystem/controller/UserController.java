package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.common.ApiResponse;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<User>> list(@RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(userService.listUsers(userId));
    }

    @PostMapping
    public ApiResponse<User> create(@RequestHeader("X-User-Id") Long userId, @RequestBody User user) {
        return ApiResponse.success("用户创建成功", userService.createUser(userId, user));
    }

    @PostMapping("/{targetUserId}/status")
    public ApiResponse<Void> updateStatus(@RequestHeader("X-User-Id") Long userId,
                                          @PathVariable Long targetUserId,
                                          @RequestParam String status) {
        userService.updateUserStatus(userId, targetUserId, status);
        return ApiResponse.success("用户状态更新成功", null);
    }
}