<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/login.css" />
<title>Log In</title>
</head>
<body>
	<h1>Log In</h1>
	
	<div class="container">
		<form action="home?new=n" method="post">
			<label for="email">Email: </label>
			<input type="email" name="email" id="email" />
			<label for="password">Password: </label>
			<input type="password" name="password" id="password" />
			<input type="submit" value="submit" />
		</form>
	</div>
	
</body>
</html>