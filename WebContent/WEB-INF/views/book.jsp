<%@page import="it.unisa.model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<% BookBean book = (BookBean) request.getAttribute("book"); %>
<title><%= book.getName() %></title>

</head>
<body>

	<h1><%= book.getName() %></h1>
	
	<p>
	<%= book.getCode() %> <br>
	<%= book.getName() %> <br>
	<%= book.getAuthor() %> <br>
	<%= book.getEditor() %> <br>
	<%= book.getGenre() %> <br>
	<%= book.getDescription() %> <br>
	<%= book.getPrice() %> <br>
	<%= book.getStock_quantity() %> <br>
	</p>z

</body>
</html>