<%@page import="it.unisa.model.PurchaseOrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordini</title>
</head>
<body>

	<% 
	ArrayList<PurchaseOrderBean> orders = (ArrayList<PurchaseOrderBean>) request.getAttribute("orders"); 
	for(PurchaseOrderBean order : orders) { %>
		<ul>
			<li>Ordine <%= order.getOrderCode() %></li>
		</ul>
		
	<% } %>
</body>
</html>