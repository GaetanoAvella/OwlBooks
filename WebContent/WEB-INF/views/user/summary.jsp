<%@page import="it.unisa.model.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.DetailOrderBean"%>
<%@page import="it.unisa.model.PurchaseOrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OwlBooks - Riepilogo ordine</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/global.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user/summary.css">
</head>
<body>

	<%@ include file="../header.jsp" %>
	
	<div class="summary-wrapper">
	    <div class="summary-card">
	        <% 
	        PurchaseOrderBean order = (PurchaseOrderBean) request.getAttribute("order"); 
	        ArrayList<DetailOrderBean> details = order.getDetails();
	        %>
	        
	        <div class="summary-header">
	            <h2>📦 Ordine #<%= order.getOrderCode() %></h2>
	            <p>Effettuato il: <strong><%= order.getOrderDate() %></strong></p>
	        </div>
	        
	        <div class="summary-info-box">
	            <div class="info-item">
	                <span class="info-label">Metodo di Pagamento</span>
	                <span class="info-value"><%= order.getPaymentMethod(true) %></span>
	            </div>
	            <div class="info-item">
	                <span class="info-label">Totale Pagato</span>
	                <span class="info-value total-price">€ <%= String.format("%.2f", order.getTotal()) %></span>
	            </div>
	        </div>
	        
	        <div class="summary-table-section">
	            <h3>Lista Acquisti</h3>
	            <table class="summary-table">
	                <thead>
	                    <tr>
	                        <th>Codice</th>
	                        <th>Nome</th>
	                        <th>Autore</th>
	                        <th>Q.tà</th>
	                        <th>Prezzo Unitario</th>
	                    </tr>
	                </thead>
	                <tbody>
	                    <% 
	                    for(int i=0; i<details.size(); i++) { 
	                        BookBean book = details.get(i).getBook();
	                    %>
	                    <tr>
	                        <td><%= book.getCode() %></td>
	                        <td class="book-name"><%= book.getName() %></td>
	                        <td><%= book.getAuthor() %></td>
	                        <td class="qty-cell"><%= details.get(i).getQuantity() %></td>
	                        <td>€ <%= String.format("%.2f", details.get(i).getPriceAtPurchase()) %></td>
	                    </tr>
	                    <% } %>
	                </tbody>
	            </table>
	        </div>
	        
	        <div class="summary-actions">
	            <a href="<%= request.getContextPath() %>/user/OrdersServlet" class="btn-back">⬅ Torna ai Miei Ordini</a>
	        </div>
	    </div>
	</div>

</body>
</html>