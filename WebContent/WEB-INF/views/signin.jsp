<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
</head>
<body>
	
	<h1>Registrati</h1>
	
	<form action="SignInServelt" method="post">
		Nome
		<input type="text" name="name"><br>
		Cognome
		<input type="text" name="surname"><br>
		Indirizzo di spedizione
		<input type="text" name="address"><br>
		Email
		<input type="email" name="email"><br>
		Password
		<input type="password" name="password"><br>
		
		<input type="submit" value="submit">
	</form>
	
</body>
</html>