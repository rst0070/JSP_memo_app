<%@ page contentType = "text/html;charset=utf-8" %>
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