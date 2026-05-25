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

	<img src="<%= request.getContextPath() %>/ImageBookServlet?code=<%= book.getCode() %>">
	
	<h1>Codice: <%= book.getCode() %></h1>
	
	<form action="<%= request.getContextPath() %>/admin/AdminBook" method="post" enctype="multipart/form-data">
		Nome<input type="text" name="name" value="<%= book.getName() %>" required><br>
		Autore<input type="text" name="author" value="<%= book.getAuthor() %>" required><br>
		Editore<input type="text" name="editor" value="<%= book.getEditor() %>" required><br>
		Genere<input type="text" name="genre" value="<%= book.getGenre() %>" required><br>
		Descrizione<textarea name="description" rows="5" cols="50"><%= book.getDescription() %></textarea><br>
		Prezzo<input type="number" name="price" min="0" value="<%= book.getPrice() %>" required><br>
		Quantità<input type="number" name="quantity" min="0" value="<%= book.getStock_quantity() %>" required><br>
		Immagine<input type="file" accept="image/*" name="image"><br>
		
		<input type="hidden" name="code" value="<%= book.getCode() %>">
		<input type="hidden" name="action" value="edit">
		
		<input type="submit" value="Conferma">
	</form>

</body>
</html>