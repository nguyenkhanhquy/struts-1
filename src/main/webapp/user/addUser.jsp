<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User Page</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/view-user.do" method="post">
		<label for="txtName">Name</label>
		<input id="txtName" name="name" type="text">
		
		<label for="txtAge">Age</label>
		<input id="txtAge" name="age" type="number">
		
		<input type="submit" value="Add">
	</form>
	
	<html:form action="/view-user.do" method="post">
		Name
		<html:text property="name" name="user"></html:text>
		
		Age
		<html:text property="age" name="user"></html:text>
		
		<html:submit>Add</html:submit>
	</html:form>
</body>
</html>
