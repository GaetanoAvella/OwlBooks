<%@page import="it.unisa.model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <% BookBean book = (BookBean) request.getAttribute("book"); %>
    <title>OwlBooks - Modifica <%= book.getName() %></title>
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_form_book.css?v=1">
    <script src="<%= request.getContextPath() %>/scripts/admin_form.js"></script>
</head>
<body>

    <%@ include file="../error.jsp" %>

    <div class="admin-dashboard">
        
        <%@ include file="admin_global.jsp" %>

        <main class="admin-content">
            <div class="content-header">
                <h1>✏️ Modifica Libro: <%= book.getCode() %></h1>
            </div>
            
            <div class="form-card">
                <form action="<%= request.getContextPath() %>/admin/AdminBook" method="post" enctype="multipart/form-data" class="admin-form">
                    
                    <div class="form-group full-width" style="text-align: center; margin-bottom: 30px;">
                        <img id="img" src="<%= request.getContextPath() %>/ImageBookServlet?code=<%= book.getCode() %>" alt="Copertina di <%= book.getName() %>">
                    </div>

                    <div class="form-grid">
                        <div class="form-group">
                            <label for="name">Nome Libro</label>
                            <input type="text" id="name" name="name" value="<%= book.getName() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="author">Autore</label>
                            <input type="text" id="author" name="author" value="<%= book.getAuthor() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="editor">Editore</label>
                            <input type="text" id="editor" name="editor" value="<%= book.getEditor() %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label for="genre">Genere</label>
                            <input type="text" id="genre" name="genre" value="<%= book.getGenre() %>" required>
                        </div>
                        
                        <div class="form-group full-width">
                            <label for="image">Aggiorna Copertina (Lascia vuoto per non modificare)</label>
                            <input type="file" id="image" accept="image/*" name="image" class="file-input">
                        </div>
                    </div>

                    <div class="form-row-extended">
					    <div class="form-group">
					        <label for="price">Prezzo (€)</label>
					        <input type="number" id="price" name="price" min="0" step="0.01" value="<%= book.getPrice() %>" required>
					    </div>
					    
					    <div class="form-group">
					        <label for="quantity">Quantità in Magazzino</label>
					        <input type="number" id="quantity" name="quantity" min="0" value="<%= book.getStock_quantity() %>" required>
					    </div>
					
					    <% if(book.getPath() != null && !book.getPath().isBlank()) { %>
					    <div class="checkbox-group">
					        <input type="checkbox" id="delete_image" name="delete_image" value="true" onchange="toggleImage()"> 
					        <label for="delete_image" class="checkbox-label">🗑️ Rimuovi copertina</label>
					    </div>
					    <% } %>
					</div>
                    
                    <div class="form-group full-width">
                        <label for="description">Trama / Descrizione</label>
                        <textarea id="description" name="description" rows="5" required><%= book.getDescription() %></textarea>
                    </div>
                    
                    <input type="hidden" name="code" value="<%= book.getCode() %>">
                    <input type="hidden" name="action" value="edit">
                    
                    <div class="form-actions">
                        <button type="submit" class="btn-submit">💾 Salva Modifiche</button>
                    </div>
                </form>
            </div>
        </main>
        
    </div>

</body>
</html>	