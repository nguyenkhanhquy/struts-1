<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập - User Management System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .login-container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }
        
        .login-header {
            text-align: center;
            margin-bottom: 30px;
        }
        
        .login-header h1 {
            color: #333;
            font-size: 2em;
            margin-bottom: 10px;
        }
        
        .login-header p {
            color: #666;
            margin: 0;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #555;
        }
        
        .form-control {
            width: 100%;
            padding: 15px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            transition: border-color 0.3s ease;
            box-sizing: border-box;
        }
        
        .form-control:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102,126,234,0.1);
        }
        
        .checkbox-group {
            display: flex;
            align-items: center;
            margin-bottom: 25px;
        }
        
        .checkbox-group input[type="checkbox"] {
            margin-right: 8px;
        }
        
        .btn-login {
            width: 100%;
            padding: 15px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s ease;
        }
        
        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102,126,234,0.3);
        }
        
        .demo-info {
            margin-top: 25px;
            padding: 15px;
            background: #f8f9fa;
            border-radius: 8px;
            border-left: 4px solid #667eea;
        }
        
        .demo-info h4 {
            margin: 0 0 10px 0;
            color: #333;
            font-size: 14px;
        }
        
        .demo-info p {
            margin: 5px 0;
            font-size: 13px;
            color: #666;
        }
    </style>
    <script>
        // Auto fill remembered username
        window.onload = function() {
            // Lấy cookie remembered username
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i].trim();
                if (cookie.indexOf('rememberedUsername=') === 0) {
                    var username = cookie.substring('rememberedUsername='.length);
                    if (username && username !== '') {
                        document.getElementById('username').value = decodeURIComponent(username);
                        document.getElementById('rememberMe').checked = true;
                    }
                    break;
                }
            }
        };
        
        function validateLogin() {
            var username = document.getElementById('username').value;
            var password = document.getElementById('password').value;
            
            if (username.trim() === '') {
                alert('Vui lòng nhập tên đăng nhập');
                return false;
            }
            
            if (password.trim() === '') {
                alert('Vui lòng nhập mật khẩu');
                return false;
            }
            
            return true;
        }
    </script>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h1>🔐 Đăng nhập</h1>
            <p>User Management System</p>
        </div>

        <!-- Hiển thị thông báo thành công -->
        <logic:messagesPresent property="success">
            <div class="alert alert-success">
                <html:messages id="message" property="success">
                    <c:out value="${message}"/>
                </html:messages>
            </div>
        </logic:messagesPresent>

        <!-- Hiển thị thông báo lỗi -->
        <logic:messagesPresent property="error">
            <div class="alert alert-error">
                <html:messages id="error" property="error">
                    <c:out value="${error}"/>
                </html:messages>
            </div>
        </logic:messagesPresent>

        <!-- Hiển thị lỗi validation -->
        <html:errors/>

        <html:form action="/login" method="post" onsubmit="return validateLogin()">
            <div class="form-group">
                <label for="username">👤 Tên đăng nhập</label>
                <html:text property="username" styleId="username" styleClass="form-control" 
                          maxlength="50" placeholder="Nhập tên đăng nhập"/>
                <html:errors property="username"/>
            </div>

            <div class="form-group">
                <label for="password">🔑 Mật khẩu</label>
                <html:password property="password" styleId="password" styleClass="form-control" 
                              maxlength="255" placeholder="Nhập mật khẩu"/>
                <html:errors property="password"/>
            </div>

            <div class="checkbox-group">
                <html:checkbox property="rememberMe" styleId="rememberMe"/>
                <label for="rememberMe">Ghi nhớ đăng nhập</label>
            </div>

            <html:submit styleClass="btn-login" value="Đăng nhập"/>
        </html:form>
        
        <div class="demo-info">
            <h4>🔰 Tài khoản demo:</h4>
            <p><strong>Admin:</strong> admin / 123456</p>
            <p><strong>User:</strong> quy / quy123</p>
        </div>
    </div>
</body>
</html>
