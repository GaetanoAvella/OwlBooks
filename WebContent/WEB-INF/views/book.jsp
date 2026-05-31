<%@page import="it.unisa.model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/global.css?v=1">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/book.css?v=1">

<% BookBean book = (BookBean) request.getAttribute("book"); %>
<title>OwlBooks - <%= book.getName() %></title>

</head>
<body>

	<%@ include file = "header.jsp" %>

	<div class="book-detail-container">
        <div class="book-image">
            <img src="<%= request.getContextPath() %>/ImageBookServlet?code=<%= book.getCode() %>" alt="Copertina di <%= book.getName() %>">
        </div>
        
        <div class="book-info">
            <h1><%= book.getName() %></h1>
            <h2 class="book-author">di <%= book.getAuthor() %></h2>
            
            <div class="book-meta">
                <p><strong>Codice ISBN:</strong> <%= book.getCode() %></p>
                <p><strong>Editore:</strong> <%= book.getEditor() %></p>
                <p><strong>Genere:</strong> <%= book.getGenre() %></p>
            </div>
            
            <div class="book-description">
                <h3>Trama</h3>
                <p><%= book.getDescription() %></p>
            </div>
            
            <div class="book-price-section">
                <span class="price">€ <%= String.format("%.2f", book.getPrice()) %></span>
            </div>
            
            <form action="CartServlet" method="post" class="add-to-cart-form">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="code" value="<%= book.getCode() %>">
                <button type="submit" class="btn-cart">🛒 Aggiungi al carrello</button>
            </form>
        </div>
    </div>

</body>
</html>