<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
</head>
<body>

<% 
if(request.getAttribute("user") == null)
	response.sendRedirect(request.getContextPath() + "IndexServlet");	
%>
	
	<h1>Registrati</h1>
	
	<form action="<%= request.getContextPath() %>/SignInServlet" method="post">
		Nome
		<input type="text" name="name" required
		value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>"><br>
		Cognome
		<input type="text" name="surname" required
		value="<%= request.getParameter("surname") != null ? request.getParameter("surname") : "" %>"><br>
		Indirizzo di spedizione
		<input type="text" name="address" required
		value="<%= request.getParameter("address") != null ? request.getParameter("address") : "" %>"><br>
		Email
		<input type="email" name="email" required>
		
		<% if(request.getAttribute("error") != null) { %>
			<%= request.getAttribute("error") %>
		<% } %>
		<br>
		
		Password
		<input type="password" name="password" required><br>
		
		<input type="submit" value="submit">
	</form>
	
</body>
</html>