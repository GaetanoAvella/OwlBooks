<%@page import="it.unisa.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/global.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user/edit-profile.css">
<title>OwlBooks - Modifica profilo</title>
</head>
<body>

    <%@ include file="../header.jsp" %>
    <%@ include file="../error.jsp" %>

    <div class="edit-wrapper">
        <div class="edit-card">
            <div class="edit-header">
                <h2>✏️ Modifica profilo</h2>
                <p>Aggiorna i tuoi dati personali</p>
            </div>
            
            <% UserBean user = (UserBean) session.getAttribute("user"); %>
            
            <form action="<%= request.getContextPath() %>/user/ProfileServlet" method="post" class="edit-form">
                <div class="form-group">
                    <label for="name">Nome</label>
                    <input type="text" id="name" name="name" value="<%= user != null ? user.getName() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="surname">Cognome</label>
                    <input type="text" id="surname" name="surname" value="<%= user != null ? user.getSurname() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="address">Indirizzo</label>
                    <input type="text" id="address" name="address" value="<%= user != null ? user.getAddress() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="<%= user != null ? user.getEmail() : "" %>" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Nuova Password (lascia vuoto per non modificare)</label>
                    <input type="password" id="password" name="password" placeholder="Inserisci la nuova password">
                </div>
                
                <div class="form-actions">
                    <a href="<%= request.getContextPath() %>/user/ProfileServlet" class="btn-cancel">Annulla</a>
                    <button type="submit" class="btn-save">💾 Salva Modifiche</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>