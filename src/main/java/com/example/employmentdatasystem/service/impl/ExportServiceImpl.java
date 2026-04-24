package com.example.employmentdatasystem.service.impl;

import com.example.employmentdatasystem.entity.EmploymentReport;
import com.example.employmentdatasystem.entity.User;
import com.example.employmentdatasystem.mapper.EmploymentReportMapper;
import com.example.employmentdatasystem.security.RoleCodes;
import com.example.employmentdatasystem.service.ExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {
    private final EmploymentReportMapper reportMapper;
    private final AuthzSupport authzSupport;

    public ExportServiceImpl(EmploymentReportMapper reportMapper, AuthzSupport authzSupport) {
        this.reportMapper = reportMapper;
        this.authzSupport = authzSupport;
    }

    @Override
    public void exportApprovedReports(Long userId, HttpServletResponse response) {
        User u = authzSupport.requireUser(userId);
        authzSupport.requireRole(u, RoleCodes.PROVINCE, RoleCodes.ADMIN);
        List<EmploymentReport> reports = reportMapper.findProvinceApproved();

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("就业数据");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("报表ID");
            header.createCell(1).setCellValue("企业ID");
            header.createCell(2).setCellValue("调查期ID");
            header.createCell(3).setCellValue("就业人数");
            header.createCell(4).setCellValue("失业人数");
            header.createCell(5).setCellValue("减少原因");
            header.createCell(6).setCellValue("审核状态");

            int rowNum = 1;
            for (EmploymentReport report : reports) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(report.getId());
                row.createCell(1).setCellValue(report.getEnterpriseId());
                row.createCell(2).setCellValue(report.getPeriodId());
                row.createCell(3).setCellValue(report.getEmploymentCount());
                row.createCell(4).setCellValue(report.getUnemploymentCount());
                row.createCell(5).setCellValue(report.getDecreaseReason() == null ? "" : report.getDecreaseReason());
                row.createCell(6).setCellValue(report.getCityAuditStatus());
            }

            String fileName = URLEncoder.encode("就业失业数据.xlsx", StandardCharsets.UTF_8.name());
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}