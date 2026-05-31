<%@page import="it.unisa.model.CartBean"%>
<%@page import="it.unisa.model.CartItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/global.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/cart.css">
<title>OwlBooks - Carrello</title>
</head>
<body>

	<%@ include file = "header.jsp" %>

	<% 
	CartBean cart = (CartBean) session.getAttribute("cart");
	String errorMsg = (String) request.getAttribute("error"); 
            
    if(errorMsg != null) { %>
	    <div class="error-message">
	        ⚠️ <%= errorMsg %>
	    </div>
    <% } %>

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