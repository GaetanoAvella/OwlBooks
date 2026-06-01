<%@page import="it.unisa.model.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>OwlBooks - Pannello Amministratore</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_index.css?v=1">
</head>
<body>

    <%@ include file="../error.jsp" %>

    <div class="admin-dashboard">
        
	<%@ include file="admin_global.jsp" %>

        <main class="admin-content">
            <div class="content-header">
                <h1>Catalogo Libri</h1>
            </div>
            
            <div class="table-container">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Codice ISBN</th>
                            <th>Nome Libro</th>	
                            <th>Prezzo</th>	
                            <th>Q.tà</th>
                            <th>Attivo</th>
                            <th>Azioni</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    ArrayList<BookBean> catalogue = (ArrayList<BookBean>) request.getAttribute("catalogue");
                    if(catalogue != null) {
                        for (BookBean book : catalogue) {
                    %>
                        <tr>
                            <td>#<%= book.getId() %></td>
                            <td><a href="<%= request.getContextPath() %>/BookServlet?code=<%= book.getCode() %>" class="code-link" target="_blank"><%= book.getCode() %></a></td>
                            <td class="book-title"><%= book.getName() %></td>
                            <td>€ <%= String.format("%.2f", book.getPrice()) %></td>
                            <td>
                                <span class="badge <%= book.getStock_quantity() > 0 ? "badge-in-stock" : "badge-out-stock" %>">
                                    <%= book.getStock_quantity() %>
                                </span>
                            </td>
                            <td>
                            <% if(book.isActive()) { %>
                            	Si
                            <% } else { %>
                            	No
                            <% } %>
                            </td>
                            <td class="actions-cell">
                                <a href="<%= request.getContextPath() %>/admin/AdminBook?code=<%= book.getCode()%>&action=edit" class="btn-action btn-edit">Modifica</a>
                                <% if(book.isActive()) { %>
                            	<a href="<%= request.getContextPath() %>/admin/AdminBook?code=<%= book.getCode() %>&action=delete" class="btn-action btn-delete">Elimina</a>
                            	<% } else { %>
                            	<a href="<%= request.getContextPath() %>/admin/AdminBook?code=<%= book.getCode() %>&action=activate" class="btn-action btn-activate">Attiva</a>
                            	<% } %>
                            </td>
                        </tr>
                    <%  } 
                    } %>
                    </tbody>
                </table>
            </div>
        </main>
        
    </div>
	
</body>
</html>