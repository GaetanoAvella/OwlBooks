<%@page import="it.unisa.model.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogo</title>
</head>
<body>
	
	<h1>Catalogo</h1>
	<table border="1">
	
		<tr>
			<th>Nome</th>
			<th>Autore</th>
			<th>Prezzo</th>	
		</tr>
		
	<%
	ArrayList<BookBean> catalogue = (ArrayList<BookBean>) request.getAttribute("catalogue");
	for (BookBean book : catalogue) {
	%>
		<tr>
			<td><a><%= book.getName() %></a></td>
			<td><a><%= book.getAuthor() %></a></td>
			<td><a><%= book.getPrice() %></a></td>
			<td><a>Modifica</a></td>
			<td><a href="<%= request.getContextPath() %>/admin/AdminIndex?code=<%= book.getCode() %>&action=delete">Elimina</a></td>
		</tr>
	
	<% } %>
	
	</table>
	
</body>
</html>