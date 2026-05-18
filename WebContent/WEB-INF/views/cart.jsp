<%@page import="it.unisa.model.CartBean"%>
<%@page import="it.unisa.model.CartItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>

	<% CartBean cart = (CartBean) session.getAttribute("cart"); %>

	<h1>Carrello</h1>
	
	<% if(cart == null) { %>
	
	<h2>Carrello vuoto</h2>
	
	<% } else { 
		for(int i=0; i<cart.sizeArrayList(); i++) {
			CartItem item = cart.get(i); %>
		<p>
			<%= item.getBook().getName() %>
			<%= item.getBook().getPrice() %>
			<%= item.getQuantity() %>
		</p>
		
	<% } %> 
		Totale<br>
	<%= cart.getTotal() %>
	
		<% if(session.getAttribute("user") == null) { %>
			<p>
			<a href="LoginServlet">Login</a>
			o
			<a href="SignInServlet">Registrati</a>
			per effettuare l'ordine.
			</p>
			<% } else { %>
			<form action="CheckOutServlet" method="get">
			<input type="submit" value="Ordina">
			</form>
			<% } %>
	
	<% } %>
	
	
	
	
</body>
</html>