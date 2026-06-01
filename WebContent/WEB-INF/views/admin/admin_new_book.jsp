<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>OwlBooks - Aggiungi Libro</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_form_book.css">
</head>
<body>

    <%@ include file="../error.jsp" %>

    <div class="admin-dashboard">
        
        <%@ include file="admin_global.jsp" %>

        <main class="admin-content">
            <div class="content-header">
                <h1>➕ Aggiungi Nuovo Libro</h1>
            </div>
            
            <div class="form-card">
                <form action="<%= request.getContextPath() %>/admin/AdminBook" method="post" enctype="multipart/form-data" class="admin-form">
                    
                    <div class="form-grid">
                        <div class="form-group">
                            <label for="code">Codice ISBN</label>
                            <input type="text" id="code" name="code" required placeholder="Es. ISBN-97888...">
                        </div>
                        
                        <div class="form-group">
                            <label for="name">Nome Libro</label>
                            <input type="text" id="name" name="name" required placeholder="Titolo dell'opera">
                        </div>
                        
                        <div class="form-group">
                            <label for="author">Autore</label>
                            <input type="text" id="author" name="author" required placeholder="Nome e Cognome">
                        </div>
                        
                        <div class="form-group">
                            <label for="editor">Editore</label>
                            <input type="text" id="editor" name="editor" required placeholder="Casa Editrice">
                        </div>
                        
                        <div class="form-group">
                            <label for="genre">Genere</label>
                            <input type="text" id="genre" name="genre" required placeholder="Es. Giallo, Fantasy, Distopico">
                        </div>
                        
                        <div class="form-group">
                            <label for="image">Copertina Libro (Immagine)</label>
                            <input type="file" id="image" accept="image/*" name="image" class="file-input">
                        </div>
                    </div>

                    <div class="form-row-small">
                        <div class="form-group">
                            <label for="price">Prezzo (€)</label>
                            <input type="number" id="price" name="price" min="0" step="0.01" required placeholder="0.00">
                        </div>
                        
                        <div class="form-group">
                            <label for="quantity">Quantità in Magazzino</label>
                            <input type="number" id="quantity" name="quantity" min="0" required placeholder="0">
                        </div>
                    </div>
                    
                    <div class="form-group full-width">
                        <label for="description">Trama / Descrizione</label>
                        <textarea id="description" name="description" rows="5" required placeholder="Inserisci la trama del libro..."></textarea>
                    </div>
                    
                    <input type="hidden" name="action" value="add">
                    
                    <div class="form-actions">
                        <button type="submit" class="btn-submit">✅ Conferma e Salva</button>
                    </div>
                </form>
            </div>
        </main>
        
    </div>

</body>
</html>