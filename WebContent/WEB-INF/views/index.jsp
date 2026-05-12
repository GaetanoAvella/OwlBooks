<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeBooks</title>
</head>
<body>
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
			<td><a href="BookServlet\?code=<%= book.getCode() %>"><%= book.getName() %></a></td>
			<td><a><%= book.getAuthor() %></a></td>
			<td><a><%= book.getPrice() %></a></td>
		</tr>
	
	<% } %>
	
	</table>
</body>
</html>