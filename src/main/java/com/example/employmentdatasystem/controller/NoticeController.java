package com.example.employmentdatasystem.controller;

import com.example.employmentdatasystem.common.ApiResponse;
import com.example.employmentdatasystem.entity.Notice;
import com.example.employmentdatasystem.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping
    public ApiResponse<Notice> publish(@RequestHeader("X-User-Id") Long userId, @RequestBody Notice notice) {
        return ApiResponse.success("通知发布成功", noticeService.publish(userId, notice));
    }

    @GetMapping
    public ApiResponse<List<Notice>> list(@RequestHeader("X-User-Id") Long userId) {
        return ApiResponse.success(noticeService.listMyNotices(userId));
    }
}