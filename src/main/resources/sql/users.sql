CREATE DATABASE IF NOT EXISTS oldzy;

USE oldzy;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(255) UNIQUE,
    number_following INT DEFAULT 0,
    number_follower INT DEFAULT 0,
    number_likes INT DEFAULT 0,
    avatar TEXT,
    reason TEXT,
    role VARCHAR(50),
    created_by VARCHAR(100),
    created_date DATETIME default CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_date DATETIME,
    birthday DATE
);

ALTER TABLE users
ADD COLUMN account_status ENUM('PENDING', 'VERIFIED', 'DISABLED') NOT NULL DEFAULT 'PENDING';

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parent_id INT DEFAULT NULL,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(1000) DEFAULT NULL,
    description TEXT,
    FOREIGN KEY (parent_id) REFERENCES CATEGORIES(id)
);

CREATE TABLE IF NOT EXISTS otp_verification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    otp_code VARCHAR(10) NOT NULL,
    expire_at DATETIME NOT NULL,
    is_used BOOLEAN DEFAULT FALSE
);

INSERT INTO `users` (`username`, `password`, `full_name`, `phone_number`, `email`, `number_following`, `number_follower`, `number_likes`, `avatar`, `role`, `created_by`, `updated_by`, `updated_date`, `birthday`) VALUES ('admin123', '$2a$10$Toh4jx/hmImjFVnWV1RpD.PjAnYIeadP69A9EBWWaMLcYATbGVZQa', 'Admin', '0911111111', 'admin@oldzy.com', '0', '0', '0', NULL, 'admin', NULL, NULL, NULL, NULL)
