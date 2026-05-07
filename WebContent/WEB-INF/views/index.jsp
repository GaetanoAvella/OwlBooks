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
	<h1 align="center">Prodotti disponibili</h1>
	
	<table border="1">
		<tr>
			<th>Codice</th>
			<th>Nome</th>
			<th>Autore</th>
			<th>Prezzo</th>
		</tr>
		
		<%ArrayList<BookBean> books = (ArrayList<BookBean>) request.getAttribute("books");
		for(BookBean book : books) {
		%>
			
		<tr>
			<td><%= book.getCodice() %></td>
			<td><%= book.getNome() %></td>
			<td><%= book.getAutore() %></td>
			<td><%= book.getPrezzo() %></td>
		</tr>
		
		<% } %>
	</table>
</body>
</html>