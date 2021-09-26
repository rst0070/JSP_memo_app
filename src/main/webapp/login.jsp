<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page import="com.rst.jsp_memo.data.MetaData" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="login.css"/>
</head>
<body>
	<form id="login-box" action="/login" method="POST">
		<h1>JSP Memo Application</h1>
		<input type="password" name="pw" placeholder="password"/>
		<input type="submit" value="login"/>
	</form>
</body>
</html>