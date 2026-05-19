<%@page import="it.unisa.model.CartBean"%>
<%@page import="it.unisa.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Riepilogo</title>
</head>
<body>
	<%
	UserBean user = (UserBean) session.getAttribute("user");
		CartBean kart = (CartBean) session.getAttribute("cart");
	%>
	
	<p>
	In consegna a <%= user.getName() %> <%= user.getSurname() %><br>
	<%= user.getAddress() %>
	</p>
	
	<h1>Totale</h1>
	<p><%= kart.getTotal() %>
	
	<h1>Selezionare metodo di pagamento</h1>
	<p>
	<form action="user/CheckOutServlet" method="post">
	
	<select name="payment_method">
		
		<option value="credit_card">Carta di credito</option>
		<option value="paypal">Paypal</option>
		<option value="on_delivery">Pagamento in consegna</option>
		
	</select>
	
	<input type="submit" value="conferma">
	</form>
	<p>
	
</body>
</html>