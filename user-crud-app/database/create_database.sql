-- Tạo database
USE master;
GO

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'UserDB')
BEGIN
    CREATE DATABASE UserDB;
END
GO

USE UserDB;
GO

-- Tạo bảng users
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='users' AND xtype='U')
BEGIN
    CREATE TABLE users (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        username NVARCHAR(50) NOT NULL UNIQUE,
        email NVARCHAR(100) NOT NULL,
        full_name NVARCHAR(100),
        password NVARCHAR(255) NOT NULL,
        created_date DATETIME DEFAULT GETDATE()
    );
END
GO

-- Tạo index cho performance
CREATE NONCLUSTERED INDEX IX_users_username ON users (username);
CREATE NONCLUSTERED INDEX IX_users_email ON users (email);
CREATE NONCLUSTERED INDEX IX_users_created_date ON users (created_date);
GO

-- Insert dữ liệu mẫu
INSERT INTO users (username, email, full_name, password, created_date) VALUES
('admin', 'admin@example.com', 'Administrator', '123456', GETDATE()),
('quy', 'quy@example.com', 'Nguyễn Khánh Quy', 'quy123', GETDATE()),
('user1', 'user1@example.com', 'User Test 1', 'user123', GETDATE()),
('user2', 'user2@example.com', 'User Test 2', 'user123', GETDATE());
GO

-- Hiển thị dữ liệu đã insert
SELECT * FROM users;
GO
