<%@page import="it.unisa.model.PurchaseOrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>OwlBooks - I Miei Ordini</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/user/orders.css">
</head>
<body>

    <header class="navbar">
        <div class="nav-logo">
            <a href="<%= request.getContextPath() %>/IndexServlet">🦉 OwlBooks</a>
        </div>
        
        <div class="nav-links">
            <div class="user-dropdown">
                <div class="profile-img">
                    <img src="<%= request.getContextPath() %>/img/user/default_user.jpg" alt="Profilo">
                </div>
                <div class="dropdown-content">
                    <a href="<%= request.getContextPath() %>/user/ProfileServlet">👤 Profilo</a>
                    <a href="<%= request.getContextPath() %>/CartServlet">🛒 Carrello</a>
                    <a href="<%= request.getContextPath() %>/user/OrdersServlet">📦 I Miei Ordini</a>
                    <a href="<%= request.getContextPath() %>/user/LogoutServlet" class="logout">🚪 Logout</a>
                </div>
            </div>
        </div>
    </header>

    <div class="orders-wrapper">
        <div class="orders-card">
            <div class="orders-header">
                <h2>Storico Ordini</h2>
                <p>Riepilogo di tutti i tuoi acquisti</p>
            </div>
            
            <div class="orders-list">
                <% 
                ArrayList<PurchaseOrderBean> orders = (ArrayList<PurchaseOrderBean>) request.getAttribute("orders"); 
                if(orders == null || orders.isEmpty()) { 
                %>
                    <div class="empty-orders">
                        <p>Non hai ancora effettuato ordini.</p>
                    </div>
                <% } else {
                    for(PurchaseOrderBean order : orders) { 
                %>
                    <div class="order-item">
                        <div class="order-info">
                            <span class="order-code">Ordine #<%= order.getOrderCode() %></span>
                            <span class="order-date">Data: <%= order.getOrderDate() %></span>
                        </div>
                        <div class="order-total-box">
                            <span class="order-total-label">Totale</span>
                            <span class="order-total-value">€ <%= order.getTotal() %></span>
                        </div>
                    </div>
                <%  }
                } %>
            </div>
        </div>
    </div>
    
</body>
</html>