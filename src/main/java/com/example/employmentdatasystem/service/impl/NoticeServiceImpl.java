package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.Notice;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.mapper.NoticeMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    private final AuthzSupport authzSupport;

    public NoticeServiceImpl(NoticeMapper noticeMapper, AuthzSupport authzSupport) {
        this.noticeMapper = noticeMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public Notice publish(Long userId, Notice notice) {
        User user = authzSupport.requireUser(userId);
        authzSupport.requireRole(user, RoleCodes.ADMIN, RoleCodes.PROVINCE);
        notice.setPublisherId(userId);
        if (notice.getTargetRole() == null || notice.getTargetRole().trim().isEmpty()) {
            notice.setTargetRole("ALL");
        }
        noticeMapper.insert(notice);
        return notice;
    }

    @Override
    public List<Notice> listMyNotices(Long userId) {
        User user = authzSupport.requireUser(userId);
        return noticeMapper.listByRole(user.getRoleCode());
    }
}