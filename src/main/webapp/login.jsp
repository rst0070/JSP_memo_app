<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="com.rst.jsp_memo.data.MetaData" %>
<%@ page import="java.net.URL" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="login.css"/>
</head>
<body>
	<h1>
		login
		<%= MetaData.getLoginPassword() %>
	</h1>
	<form id="login-box" action="/login" method="POST">
		<input type="password" name="pw"/>
		<input type="submit"/>
	</form>
</body>
</html>