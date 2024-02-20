<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String redirect = request.getParameter("redirect");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/signup.css" />
<title>Insert title here</title>
</head>
<body>
	<h1>Sign Up</h1>
	
	<div class="container">
		<form action="home?new=y" method="post">
			<label for="name">Name: </label>
			<input type="text" name="name" id="name"  required/>
			<label for="email">Email: </label>
			<input type="email" name="email" id="email"  required/>
			<label for="password">Password: </label>
			<input type="password" name="password" id="password" required />
			<input type="submit" value="submit" />
		</form>
		<% if (redirect != null  && redirect.equals("y") ) { %>
			<script type="text/javascript">
				alert("you have an account");
			</script>
		<% } %>
		
	</div>
</body>
</html>