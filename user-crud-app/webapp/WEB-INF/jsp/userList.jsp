<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><bean:message key="page.title.userlist"/></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <script>
        function confirmDelete(username) {
            return confirm('Bạn có chắc chắn muốn xóa user "' + username + '" không?');
        }
        
        function confirmLogout() {
            return confirm('Bạn có chắc chắn muốn đăng xuất không?');
        }
    </script>
</head>
<body>
    <div class="container">
        <!-- Header với thông tin user đăng nhập -->
        <header>
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
                <h1><bean:message key="page.title.userlist"/></h1>
                <div class="user-info">
                    <span>Xin chào, <strong>${sessionScope.userSession.fullName}</strong></span>
                    <html:link action="/logout" styleClass="btn btn-secondary" 
                              onclick="return confirmLogout()" style="margin-left: 15px;">
                        🚪 Đăng xuất
                    </html:link>
                </div>
            </div>
        </header>

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

        <div class="toolbar">
            <html:link action="/userCreate" styleClass="btn btn-primary">
                ➕ <bean:message key="button.create"/>
            </html:link>
            <span class="user-count">Tổng số: ${totalUsers} user(s)</span>
        </div>

        <div class="table-container">
            <logic:empty name="userList">
                <div class="no-data">
                    <p><bean:message key="message.no.users"/></p>
                </div>
            </logic:empty>

            <logic:notEmpty name="userList">
                <table class="user-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th><bean:message key="label.username"/></th>
                            <th><bean:message key="label.email"/></th>
                            <th><bean:message key="label.fullname"/></th>
                            <th><bean:message key="label.createddate"/></th>
                            <th><bean:message key="label.actions"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <logic:iterate id="user" name="userList" type="com.example.model.User">
                            <tr>
                                <td><c:out value="${user.id}"/></td>
                                <td>
                                    <c:out value="${user.username}"/>
                                    <c:if test="${user.username == sessionScope.userSession.username}">
                                        <span style="color: #007bff; font-weight: bold;"> (Bạn)</span>
                                    </c:if>
                                </td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.fullName}"/></td>
                                <td>
                                    <fmt:formatDate value="${user.createdDate}" pattern="dd/MM/yyyy HH:mm"/>
                                </td>
                                <td class="actions">
                                    <html:link action="/userEdit" paramId="id" paramName="user" paramProperty="id" 
                                              styleClass="btn btn-edit">
                                        ✏️ <bean:message key="button.edit"/>
                                    </html:link>
                                    
                                    <c:if test="${user.username != sessionScope.userSession.username}">
                                        <html:link action="/userDelete" paramId="id" paramName="user" paramProperty="id" 
                                                  styleClass="btn btn-delete"
                                                  onclick="return confirmDelete('${user.username}')">
                                            🗑️ <bean:message key="button.delete"/>
                                        </html:link>
                                    </c:if>
                                    
                                    <c:if test="${user.username == sessionScope.userSession.username}">
                                        <span class="btn btn-disabled" title="Không thể xóa tài khoản của chính mình">
                                            🚫 <bean:message key="button.delete"/>
                                        </span>
                                    </c:if>
                                </td>
                            </tr>
                        </logic:iterate>
                    </tbody>
                </table>
            </logic:notEmpty>
        </div>
        
        <!-- Thông tin session -->
        <div class="session-info" style="margin-top: 30px; padding: 15px; background: #f8f9fa; border-radius: 5px; font-size: 14px; color: #666;">
            <p><strong>Thông tin phiên làm việc:</strong></p>
            <p>Đăng nhập lúc: <fmt:formatDate value="${sessionScope.userSession.loginTime}" pattern="dd/MM/yyyy HH:mm:ss"/></p>
            <p>User ID: ${sessionScope.userSession.userId}</p>
        </div>
    </div>
</body>
</html>
