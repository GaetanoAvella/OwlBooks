<%@page import="it.unisa.model.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Amministratore</title>
</head>
<body>

	<ul>
		<li><a href="<%= request.getContextPath() %>/IndexServlet">Index</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/AdminBook?action=add">Aggiungi libro</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/AdminOrderList">Ordini</a></li>
		<li><a href="<%= request.getContextPath() %>/admin/AdminLogout">Logout</a></li>
	</ul>
	
	<h1>Catalogo</h1>
	<table border="1">
	
		<tr>
			<th>Id</th>
			<th>Codice</th>
			<th>Nome</th>	
			<th>Prezzo</th>	
			<th>Quantità</th>	
		</tr>
		
	<%
	ArrayList<BookBean> catalogue = (ArrayList<BookBean>) request.getAttribute("catalogue");
	for (BookBean book : catalogue) {
	%>
		<tr>
			<td><%= book.getId() %></td>
			<td><a href="<%= request.getContextPath() %>/BookServlet?code=<%= book.getCode() %>"><%= book.getCode() %></a></td>
			<td><%= book.getName() %></td>
			<td><%= book.getPrice() %></td>
			<td><%= book.getStock_quantity() %></td>
			<td><a href="<%= request.getContextPath() %>/admin/AdminBook?code=<%= book.getCode()%>&action=edit">Modifica</a></td>
			<td><a href="<%= request.getContextPath() %>/admin/AdminBook?code=<%= book.getCode() %>&action=delete">Elimina</a></td>
		</tr>
	
	<% } %>
	
	</table>
	
</body>
</html>