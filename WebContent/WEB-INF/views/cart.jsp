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
	
	<% if(cart == null || cart.sizeArrayList() == 0) { %>
	
	<h2>Carrello vuoto</h2>
	
	<% } else { 
		for(int i=0; i<cart.sizeArrayList(); i++) {
			CartItem item = cart.get(i); %>
		
			Nome <%= item.getBook().getName() %>
			Prezzo <%= item.getBook().getPrice() %>
			
			<form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="code" value="<%= item.getBook().getCode() %>">
                    <input type="number" name="quantity" value="<%= item.getQuantity() %>">
                    <input type="submit" value="Aggiorna">
           	</form>
            
            Subtotale <%= item.subTotal() %>
            
            <form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="code" value="<%= item.getBook().getCode() %>">
                    <input type="submit" value="Rimuovi">
           	</form>
		
	<% } %> 
		Totale<br>
	<%= cart.getTotal() %><br>
			
			<form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="clear">
                    <input type="submit" value="Svuota">
           	</form>
	
		<% if(session.getAttribute("user") == null) { %>
		<p>
		<a href="LoginServlet">Login</a>
		o
		<a href="SignInServlet">Registrati</a>
		per effettuare l'ordine.
		</p>
		<% } else { %>
		<a href="user/CheckOutServlet">Ordina</a>
		<% } %>
	
	<% } %>
	
	
	
	
</body>
</html>