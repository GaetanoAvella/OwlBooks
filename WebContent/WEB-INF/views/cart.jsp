<%@page import="it.unisa.model.CartBean"%>
<%@page import="it.unisa.model.CartItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/cart.css">
<title>Carrello</title>
</head>
<body>

    <header class="navbar">
        <div class="nav-logo">
            <a href="IndexServlet">🦉 OwlBooks</a>
        </div>
        
        <div class="nav-links">
            <% if (session.getAttribute("user") == null) {%>
                <a href="CartServlet" class="btn-nav" id="cart-btn">🛒 Carrello</a>
                <a href="LoginServlet" class="btn-nav" id="login-btn">Login</a>
                <a href="SignInServlet" class="btn-nav" id="signin-btn">Registrati</a>
            <% } else { %>
                <div class="user-dropdown">
                    <div class="profile-img">
                        <img src="<%= request.getContextPath() %>/img/user/default_user.jpg" alt="Profilo">
                    </div>
                    <div class="dropdown-content">
                        <a href="user/ProfileServlet">👤 Profilo</a>
                        <a href="CartServlet">🛒 Carrello</a>
                        <a href="user/OrdersServlet">📦 I Miei Ordini</a>
                        <a href="user/LogoutServlet" class="logout">🚪 Logout</a>
                    </div>
                </div>
            <% } %>
        </div>
    </header>

	<% CartBean cart = (CartBean) session.getAttribute("cart"); %>

    <div class="cart-wrapper">
    
        <% if(cart == null || cart.sizeArrayList() == 0) { %>
            <div class="cart-items-section" style="width: 100%;">
                <h2>Carrello</h2>
                <div class="empty-cart-msg">
                    <h3>Carrello vuoto</h3>
                </div>
            </div>
        <% } else { %>
        
        <div class="cart-items-section">
            <h2>Carrello</h2>
            
            <% 
                for(int i=0; i<cart.sizeArrayList(); i++) {
                    CartItem item = cart.get(i); 
            %>
                <div class="cart-item">
                    <div class="item-info">
                        <img src="<%= request.getContextPath() %>/ImageBookServlet?code=<%= item.getBook().getCode() %>" alt="Copertina">
                        <div class="item-details">
                            <h4><%= item.getBook().getName() %></h4>
                            <p>Prezzo: € <%= item.getBook().getPrice() %></p>
                        </div>
                    </div>
                    
                    <div class="item-quantity">
                        <form action="CartServlet" method="post" class="update-form">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="code" value="<%= item.getBook().getCode() %>">
                            <input type="number" name="quantity" min="1" max="<%= item.getBook().getStock_quantity() %>" value="<%= item.getQuantity() %>" class="quantity-input">
                            <input type="submit" value="Aggiorna" class="btn-update">
                        </form>
                    </div>
                    
                    <div class="item-price">
                        Subtotale: € <%= item.subTotal() %>
                    </div>
                    
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="code" value="<%= item.getBook().getCode() %>">
                        <input type="submit" value="Rimuovi" class="btn-remove">
                    </form>
                </div>
            <% } %> 
            
            <div class="empty-cart-container">
                <form action="CartServlet" method="post">
                    <input type="hidden" name="action" value="clear">
                    <input type="submit" value="Svuota Carrello" class="btn-empty-cart">
                </form>
            </div>
            
        </div>

        <div class="cart-summary-section">
            <h3>Riepilogo Ordine</h3>
            
            <div class="summary-total">
                <span>Totale:</span>
                <span class="total-price">€ <%= cart.getTotal() %></span>
            </div>
            
            <% if(session.getAttribute("user") == null) { %>
            <div class="login-prompt">
                <p>
                    <a href="LoginServlet">Login</a> o <a href="SignInServlet">Registrati</a> per effettuare l'ordine.
                </p>
            </div>
            <% } else { %>
            <a href="user/CheckOutServlet" class="btn-checkout">Procedi all'acquisto</a>
            <% } %>
            
        </div>
        <% } %>
        
    </div>
	
</body>
</html>