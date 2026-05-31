<%@page import="it.unisa.model.CartBean"%>
<%@page import="it.unisa.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>OwlBooks - Riepilogo Ordine</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/global.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/user/checkout.css">
</head>
<body>

    <%@ include file="../header.jsp" %>

    <%
        UserBean user = (UserBean) session.getAttribute("user");
        CartBean kart = (CartBean) session.getAttribute("cart");
        String errorMsg = (String) request.getAttribute("error"); 
        
        if(errorMsg != null) { %>
           <div class="error-message">
               ⚠️ <%= errorMsg %>
           </div>
       <% } %>
    
    <div class="checkout-wrapper">
        <div class="checkout-card">
            <div class="checkout-header">
                <h2>Riepilogo Ordine</h2>
                <p>Controlla i tuoi dati e procedi al pagamento</p>
            </div>
            
            <div class="checkout-info">
                <div class="info-group">
                    <span class="info-label">In consegna a:</span>
                    <span class="info-value"><%= user.getName() %> <%= user.getSurname() %></span>
                </div>
                <div class="info-group">
                    <span class="info-label">Indirizzo:</span>
                    <span class="info-value"><%= user.getAddress() %></span>
                </div>
                <div class="info-group total-group">
                    <span class="info-label">Totale da pagare:</span>
                    <span class="info-value total-price">€ <%= kart.getTotal() %></span>
                </div>
            </div>
            
            <form action="<%= request.getContextPath() %>/user/CheckOutServlet" method="post" class="checkout-form">
                <div class="form-group">
                    <label for="payment_method">Metodo di Pagamento</label>
                    <select name="payment_method" id="payment_method">
                        <option value="credit_card">Carta di credito</option>
                        <option value="paypal">Paypal</option>
                        <option value="on_delivery">Pagamento alla consegna</option>
                    </select>
                </div>
                
                <button type="submit" class="btn-submit">Conferma Ordine</button>
            </form>
        </div>
    </div>
    
</body>
</html>