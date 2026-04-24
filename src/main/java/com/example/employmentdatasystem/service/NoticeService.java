package com.example.employmentdatasystem.service;

import com.example.employmentdatasystem.entity.Notice;

import java.util.List;

public interface NoticeService {
    Notice publish(Long userId, Notice notice);
    List<Notice> listMyNotices(Long userId);
}