<%@page import="it.unisa.model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<% BookBean book = (BookBean) request.getAttribute("book"); %>

<title><%= book.getName() %>></title>
</head>
<body>

<% if(book.getPath() == null || book.getPath().equals("")) { %>
		<img src="<%= request.getContextPath() %>/img/book/default_book.png">
	<% } else { %>
		<img src="ImageBookServlet?code=<%= book.getCode() %>">
	<% } %>
	
	<h1>Codice: <%= book.getCode() %></h1>
	
	<form action="<%= request.getContextPath() %>/admin/AdminBook" method="post">
		Nome<input type="text" name="name" value="<%= book.getName() %>" required>
		Autore<input type="text" name="author" value="<%= book.getAuthor() %>" required>
		Editore<input type="text" name="editor" value="<%= book.getEditor() %>" required>
		Genere<input type="text" name="genre" value="<%= book.getGenre() %>" required>
		Descrizione<input type="text" name="description" value="<%= book.getDescription() %>" required>
		Prezzo<input type="number" name="price" min="0" value="<%= book.getPrice() %>" required>
		Quantità<input type="number" name="quanitity" min="0" value="<%= book.getStock_quantity() %>" required>
		
		<input type="submit" value="Conferma">
	</form>

</body>
</html>