<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_global.css">

<aside class="admin-sidebar">
        <div class="admin-logo">
            <h2>⚙️ Admin Panel</h2>
        </div>
        <ul class="admin-menu">
            <li><a href="<%= request.getContextPath() %>/IndexServlet">🏠 Torna al Sito</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/AdminBook?action=add">➕ Aggiungi libro</a></li>
            <li><a href="<%= request.getContextPath() %>/admin/AdminOrderList">📦 Gestione Ordini</a></li>
            <li><a href="<%= request.getContextPath() %>/user/LogoutServlet" class="logout-link">🚪 Logout</a></li>
        </ul>
</aside>