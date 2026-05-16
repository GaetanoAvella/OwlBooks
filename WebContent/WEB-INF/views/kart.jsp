<%@page import="it.unisa.model.KartBean"%>
<%@page import="it.unisa.model.KartItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>

	<% KartBean kart = (KartBean) session.getAttribute("kart"); %>

	<h1>Carrello</h1>
	
	<% if(kart == null) { %>
	
	<h2>Carrello vuoto</h2>
	
	<% } else { 
		for(int i=0; i<kart.sizeArrayList(); i++) {
			KartItem item = kart.get(i); %>
		<p>
			<h2><%= item.getBook().getName() %></h2>
			<%= item.getBook().getPrice() %>
			<%= item.getQuantity() %>
		</p>
		
	<% } %> 
		Totale<br>
	<%= kart.getTotal() %>
	<% } %>
	
	<% if(session.getAttribute("user") == null) { %>
	<p>
	<a href="LoginServlet">Login</a>
	o
	<a href="SignInServlet">Registrati</a>
	per effettuare l'ordine.
	</p>
	<% } else { %>
	<form action="SummaryServlet" method="get">
	<input type="submit" value="Ordina">
	</form>
	<% } %>
	
	
</body>
</html>