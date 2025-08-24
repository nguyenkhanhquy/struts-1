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
    <title>
        <c:choose>
            <c:when test="${mode == 'edit'}">
                <bean:message key="page.title.useredit"/>
            </c:when>
            <c:otherwise>
                <bean:message key="page.title.usercreate"/>
            </c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <script>
        function validateForm() {
            var username = document.getElementById('username').value;
            var email = document.getElementById('email').value;
            var password = document.getElementById('password').value;
            var confirmPassword = document.getElementById('confirmPassword').value;
            
            if (username.trim() === '') {
                alert('Tên đăng nhập không được để trống');
                return false;
            }
            
            if (email.trim() === '') {
                alert('Email không được để trống');
                return false;
            }
            
            // Kiểm tra password chỉ khi tạo mới hoặc có nhập password
            if ('${mode}' !== 'edit' && password.trim() === '') {
                alert('Mật khẩu không được để trống');
                return false;
            }
            
            if (password.trim() !== '' && password !== confirmPassword) {
                alert('Mật khẩu xác nhận không khớp');
                return false;
            }
            
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <header>
            <h1>
                <c:choose>
                    <c:when test="${mode == 'edit'}">
                        <bean:message key="page.title.useredit"/>
                    </c:when>
                    <c:otherwise>
                        <bean:message key="page.title.usercreate"/>
                    </c:otherwise>
                </c:choose>
            </h1>
        </header>

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

        <div class="form-container">
            <html:form action="/userSave" method="post" onsubmit="return validateForm()">
                <!-- Hidden field cho ID khi edit -->
                <html:hidden property="id"/>
                
                <div class="form-group">
                    <label for="username"><bean:message key="label.username"/> <span class="required">*</span></label>
                    <html:text property="username" styleId="username" styleClass="form-control" maxlength="50"/>
                    <html:errors property="username"/>
                </div>

                <div class="form-group">
                    <label for="email"><bean:message key="label.email"/> <span class="required">*</span></label>
                    <html:text property="email" styleId="email" styleClass="form-control" maxlength="100"/>
                    <html:errors property="email"/>
                </div>

                <div class="form-group">
                    <label for="fullName"><bean:message key="label.fullname"/></label>
                    <html:text property="fullName" styleId="fullName" styleClass="form-control" maxlength="100"/>
                    <html:errors property="fullName"/>
                </div>

                <div class="form-group">
                    <label for="password">
                        <bean:message key="label.password"/> 
                        <c:choose>
                            <c:when test="${mode == 'edit'}">
                                (Để trống nếu không muốn thay đổi)
                            </c:when>
                            <c:otherwise>
                                <span class="required">*</span>
                            </c:otherwise>
                        </c:choose>
                    </label>
                    <html:password property="password" styleId="password" styleClass="form-control" maxlength="255"/>
                    <html:errors property="password"/>
                </div>

                <div class="form-group">
                    <label for="confirmPassword"><bean:message key="label.confirmpassword"/></label>
                    <html:password property="confirmPassword" styleId="confirmPassword" styleClass="form-control" maxlength="255"/>
                    <html:errors property="confirmPassword"/>
                </div>

                <div class="form-actions">
                    <html:submit styleClass="btn btn-primary">
                        <bean:message key="button.save"/>
                    </html:submit>
                    <html:link action="/userList" styleClass="btn btn-secondary">
                        <bean:message key="button.cancel"/>
                    </html:link>
                </div>
            </html:form>
        </div>
    </div>
</body>
</html>
