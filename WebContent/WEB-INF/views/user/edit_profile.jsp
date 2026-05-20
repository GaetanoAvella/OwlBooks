<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica profilo</title>
</head>
<body>
	<h1>Modifica profilo</h1>
	
	<form action="<%= request.getContextPath() %>/user/ProfileServlet" method="post">
		Nome <input type="text" name="name">
		Cognome <input type="text" name="surname">
		Indirizzo <input type="text" name="address">
		Email <input type="email" name="email">
		Password <input type="password" name="password">
		
		<input type="submit">
	</form>
	
</body>
</html>