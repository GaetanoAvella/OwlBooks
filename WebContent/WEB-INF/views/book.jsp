<%@page import="it.unisa.model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<% BookBean book = (BookBean) request.getAttribute("book"); %>
<title><%= book.getName() %></title>

</head>
<body>

	<img src="<%= request.getContextPath() %>/ImageBookServlet?code=<%= book.getCode() %>">
	
	<h1><%= book.getName() %></h1>
	
	<p>
	<%= book.getCode() %> <br>
	<%= book.getName() %> <br>
	<%= book.getAuthor() %> <br>
	<%= book.getEditor() %> <br>
	<%= book.getGenre() %> <br>
	<%= book.getDescription() %> <br>
	<%= book.getPrice() %> <br>
	</p>
	
	<% if(!"true".equals(session.getAttribute("admin"))) { %>
	
	<form action="CartServlet" method="post">
		<input type="hidden" name="action" value="add">
		<input type="hidden" name="code" value="<%= book.getCode() %>">
		<input type="submit" value="Aggiungi al carrello">
	</form>

	<% } %>

</body>
</html>