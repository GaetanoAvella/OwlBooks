<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css?v=1">
<script src="<%= request.getContextPath() %>/scripts/menubar.js"></script>

<header class="navbar">
        <div class="nav-logo">
            <a href="<%= request.getContextPath() %>/IndexServlet">🦉 OwlBooks</a>
        </div>
        
        <div class="nav-links">
            <% if (session.getAttribute("user") == null) {%>
            	<a href="<%= request.getContextPath() %>/CartServlet" class="btn-nav" id="cart-btn">🛒 Carrello</a>
                <a href="<%= request.getContextPath() %>/LoginServlet" class="btn-nav" id="login-btn">Login</a>
                <a href="<%= request.getContextPath() %>/SignInServlet" class="btn-nav" id="signin-btn">Registrati</a>
            <% } else { %>
                <div class="user-dropdown">
                    <div class="profile-img" onclick="toggleMenu()">
                        <img src="<%= request.getContextPath() %>/img/user/default_user.jpg" alt="Profilo">
                    </div>
                    <div class="dropdown-content" id="dropdown-menu">
                        <a href="<%= request.getContextPath() %>/user/ProfileServlet">👤 Profilo</a>
                        <% if("true".equals(session.getAttribute("admin"))) { %>
                        	<a href="<%= request.getContextPath() %>/admin/AdminIndex">⚙️ Pannello Admin </a>
                        <% } %>
                        <a href="<%= request.getContextPath() %>/CartServlet">🛒 Carrello</a>
                        <a href="<%= request.getContextPath() %>/user/OrdersServlet">📦 I Miei Ordini</a>
                        <a href="<%= request.getContextPath() %>/user/LogoutServlet" class="logout">🚪 Logout</a>
                    </div>
                </div>
            <% } %>
        </div>
</header>