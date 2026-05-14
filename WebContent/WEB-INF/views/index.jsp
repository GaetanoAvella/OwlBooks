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

	<form method="get" action="IndexServlet">
		
		<label for="order">Ordina i libri per: </label>
		<select name="order">
			
			<option value="az">Alfabetico(A-Z)</option>
			<option value="za">Alfabetico(Z-A)</option>
			<option value="pricelow">Dal meno costoso</option>
			<option value="pricehigh">Dal più costoso</option>
			
		</select>
		
		<input type="submit" value="Ordina">
		
	</form>

	<h1>Generi</h1>
	<ul>
	<%
	ArrayList<String> genres = (ArrayList<String>) request.getAttribute("genres");
	for (String genre : genres) {
	%>
		
		<li><a href="IndexServlet?filter=<%= genre %>"><%= genre %></a></li>
		
	<% } %>
	</ul>

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
			<td><a href="<%= request.getContextPath() %>/BookServlet?code=<%= book.getCode() %>"><%= book.getName() %></a></td>
			<td><a><%= book.getAuthor() %></a></td>
			<td><a><%= book.getPrice() %></a></td>
		</tr>
	
	<% } %>
	
	</table>
</body>
</html>