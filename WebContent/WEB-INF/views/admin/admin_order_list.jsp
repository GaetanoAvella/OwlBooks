<%@page import="it.unisa.model.PurchaseOrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini</title>
</head>
<body>

<form action="<%= request.getContextPath() %>/admin/AdminOrderList" method="post">
	<input type="date" name="from" value="<%= request.getAttribute("from_date") %>"> 
	<input type="date" name="to" value="<%= request.getAttribute("to_date") %>"> 
	
	<input type="submit" value="Cerca"> 		
</form>

<% if(request.getAttribute("error") != null) { %>

	<p><%= request.getAttribute("error") %>

<% 
	}
	ArrayList<PurchaseOrderBean> orders = (ArrayList<PurchaseOrderBean>) request.getAttribute("orders");
%>

<table border="1">

	<tr>
	
		<th>Id</th>
		<th>Id utente</th>
		<th>Codice ordine</th>
		<th>Data acquisto</th>
		<th>Metodo di pagamento</th>
		<th>Totale</th>
	
	</tr>
	
	<% for(PurchaseOrderBean order : orders) { %>
	
		<tr>
			<td><%= order.getId() %></td>
			<td><%= order.getUserId() %></td>
			<td><a href="<%= request.getContextPath() %>/admin/AdminOrderDetail?code=<%= order.getOrderCode() %>"><%= order.getOrderCode() %></a></td>
			<td><%= order.getOrderDate() %></td>
			<td><%= order.getPaymentMethod() %></td>
			<td><%= order.getTotal() %></td>
		</tr>
	
	<% } %>

</table>

</body>
</html>