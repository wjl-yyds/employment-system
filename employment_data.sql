CREATE DATABASE IF NOT EXISTS employment_data_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE employment_data_system;
SET NAMES utf8mb4;

DROP TABLE IF EXISTS report_audit_record;
DROP TABLE IF EXISTS filing_audit_record;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS employment_report;
DROP TABLE IF EXISTS survey_period_config;
DROP TABLE IF EXISTS enterprise_info;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;

CREATE TABLE sys_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_code VARCHAR(50) NOT NULL UNIQUE COMMENT 'ENTERPRISE/CITY/PROVINCE/ADMIN',
    role_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    real_name VARCHAR(100) NOT NULL,
    role_code VARCHAR(50) NOT NULL,
    city_code VARCHAR(50) DEFAULT NULL,
    enterprise_id BIGINT DEFAULT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE enterprise_info (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    enterprise_name VARCHAR(200) NOT NULL,
    social_credit_code VARCHAR(100) NOT NULL UNIQUE,
    city_code VARCHAR(50) NOT NULL,
    contact_person VARCHAR(100) NOT NULL,
    contact_phone VARCHAR(30) NOT NULL,
    filing_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    filing_reason VARCHAR(500) DEFAULT NULL,
    created_by BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE survey_period_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    period_name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'INACTIVE',
    created_by BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE employment_report (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    enterprise_id BIGINT NOT NULL,
    period_id BIGINT NOT NULL,
    employment_count INT NOT NULL,
    unemployment_count INT NOT NULL,
    decrease_reason VARCHAR(500) DEFAULT NULL,
    submitter_id BIGINT NOT NULL,
    city_audit_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    city_audit_comment VARCHAR(500) DEFAULT NULL,
    city_auditor_id BIGINT DEFAULT NULL,
    city_audit_time DATETIME DEFAULT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY idx_report_enterprise (enterprise_id),
    KEY idx_report_period (period_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE filing_audit_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    enterprise_id BIGINT NOT NULL,
    auditor_id BIGINT NOT NULL,
    audit_status VARCHAR(20) NOT NULL,
    audit_comment VARCHAR(500) DEFAULT NULL,
    audit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE report_audit_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    report_id BIGINT NOT NULL,
    auditor_id BIGINT NOT NULL,
    audit_status VARCHAR(20) NOT NULL,
    audit_comment VARCHAR(500) DEFAULT NULL,
    audit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE notice (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    target_role VARCHAR(50) NOT NULL DEFAULT 'ALL',
    publisher_id BIGINT NOT NULL,
    publish_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PUBLISHED'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO sys_role(role_code, role_name) VALUES
('ENTERPRISE', 'Enterprise User'),
('CITY', 'City User'),
('PROVINCE', 'Province User'),
('ADMIN', 'Administrator');

INSERT INTO sys_user(username, password, real_name, role_code, city_code) VALUES
('enterprise1','123456','Enterprise A','ENTERPRISE','KM'),
('city1','123456','Kunming Auditor','CITY','KM'),
('province1','123456','Province Analyst','PROVINCE',NULL),
('admin1','123456','System Admin','ADMIN',NULL);