<%@page import="it.unisa.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo</title>
</head>
<body>

	<%
	UserBean user = (UserBean) session.getAttribute("user");
	%>
	<h1><%= user.getName() %> <%= user.getSurname() %></h1>
	<p>
	Indirizzo <%= user.getAddress() %> <br>
	Email <%= user.getEmail() %> <br>
	</p>

</body>
</html>