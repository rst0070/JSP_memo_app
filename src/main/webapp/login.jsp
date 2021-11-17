<%@ page contentType = "text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="static/css/login/login.css"/>
	<script src="static/js/login/main.js"></script>
</head>
<body>
	<button id="changeform">change form</button>
	<form id="login-box" action="/login" method="POST">
		<h1>JSP Memo Application Login</h1>
		<input type="password" name="pw" placeholder="password"/><br/>
		<input type="submit" value="login"/>
	</form>
	<form id="changepw-box" action="/login/change" method="POST">
		<h1>Change Password</h1>
		<input type="password" name="pw" placeholder="present password"/><br/>
		<input type="password" name="new_pw" placeholder="new password"/><br/>
		<input type="submit" value="change"/>
	</form>
</body>
</html>