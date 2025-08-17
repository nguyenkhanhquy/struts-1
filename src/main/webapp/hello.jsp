<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Page</title>
</head>
<body>
	<h1>Hello Struts</h1>
	<h2>
		<bean:write name="user" property="name" />
	</h2>
	<h2>
		<bean:write name="user" property="age" format="#####" />
	</h2>
</body>
</html>
