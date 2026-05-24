<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuovo libro</title>
</head>
<body>

	<form action="<%= request.getContextPath() %>/admin/AdminBook" method="post">
		Codice<input type="text" name="code" required><br>
		Nome<input type="text" name="name" required><br>
		Autore<input type="text" name="author" required><br>
		Editore<input type="text" name="editor" required><br>
		Genere<input type="text" name="genre" required><br>
		Descrizione<textarea name="description" rows="5" cols="50"></textarea><br>
		Prezzo<input type="number" name="price" min="0" required><br>
		Quantità<input type="number" name="quantity" min="0" required><br>
		
		<input type="hidden" name="action" value="add">
		
		<input type="submit" value="Conferma">
	</form>

</body>
</html>