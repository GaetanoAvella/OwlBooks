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

	<% if (session.getAttribute("user") == null) {%>
	<h1><a href="LoginServlet">Login</a></h1>
	<h1><a href="SignInServelt">Registrati</a></h1>
	<% } else { %>
	<h1><a href="ProfileServlet">Profilo</a></h1>
	<h1><a href="OrdersServlet">Ordini</a></h1>
	<h1><a href="LogoutServlet">Logout</a></h1>
	
	<% } %>
	<h1><a href="CartServlet">Carrello</a></h1>

	<form method="get" action="IndexServlet">
		
		<label for="sort">Ordina i libri per: </label>
		<select name="sort">
			
			<option value="az">Alfabetico(A-Z)</option>
			<option value="za">Alfabetico(Z-A)</option>
			<option value="pricelow">Dal meno costoso</option>
			<option value="pricehigh">Dal più costoso</option>
			
		</select>
		
		<% if(request.getParameter("filter") != null) { %>
		<input type="hidden" name="filter" value="<%= request.getParameter("filter") %>">
		<% } %>
		
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
			<td><a href="BookServlet?code=<%= book.getCode() %>"><%= book.getName() %></a></td>
			<td><a><%= book.getAuthor() %></a></td>
			<td><a><%= book.getPrice() %></a></td>
		</tr>
	
	<% } %>
	
	</table>
</body>
</html>