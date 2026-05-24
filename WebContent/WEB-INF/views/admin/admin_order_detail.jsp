<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.DetailOrderBean"%>
<%@page import="it.unisa.model.PurchaseOrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<% 
PurchaseOrderBean order = (PurchaseOrderBean) request.getAttribute("order");
ArrayList<DetailOrderBean> details = order.getDetails();
%>

<title><%= order.getOrderCode() %></title>
</head>
<body>

	Id: <%= order.getId() %><br>
	Id utente: <%= order.getUserId() %><br>
	Codice ordine: <%= order.getOrderCode() %><br>
	Data ordini: <%= order.getOrderDate() %><br>
	Metodo di pagamento: <%= order.getPaymentMethod() %><br>
	
	<table border="1">
	
		<tr>
		
			<th>Id</th>
			<th>Nome</th>
			<th>Quantità</th>
			<th>Prezzo</th>
		
		</tr>
		
		<% for(DetailOrderBean detail : details) { %>
		
		<tr>
			<td><%= detail.getBookId() %></td>
			<td><%= detail.getBook().getName() %></td>
			<td><%= detail.getQuantity()%></td>
			<td><%= detail.getPriceAtPurchase() %></td>
		</tr>
		
		<% } %>
		
	</table>
	
	Totale: <%= order.getTotal() %>
	
</body>
</html>