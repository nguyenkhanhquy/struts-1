<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View User Page</title>
</head>
<body>
	<p>Name: <bean:write name="user" property="name"/></p>
	<p>Age: <bean:write name="user" property="age" format="##"/></p>
</body>
</html>
