<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.DetailOrderBean"%>
<%@page import="it.unisa.model.PurchaseOrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <% 
    PurchaseOrderBean order = (PurchaseOrderBean) request.getAttribute("order");
    ArrayList<DetailOrderBean> details = order.getDetails();
    %>
    <title>OwlBooks - Dettaglio <%= order.getOrderCode() %></title>
    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_order_detail.css">
</head>
<body>

    <%@ include file="../error.jsp" %>

    <div class="admin-dashboard">
        
        <%@ include file="admin_global.jsp" %>

        <main class="admin-content">
            <div class="content-header">
                <h1>🔍 Dettaglio Ordine: <%= order.getOrderCode() %></h1>
            </div>
            
            <div class="order-summary-card">
                <div class="summary-grid">
                    <div class="summary-item">
                        <span class="summary-label">ID Ordine:</span>
                        <span class="summary-value">#<%= order.getId() %></span>
                    </div>
                    <div class="summary-item">
                        <span class="summary-label">ID Utente:</span>
                        <span class="summary-value"><%= order.getUserId() %></span>
                    </div>
                    <div class="summary-item">
                        <span class="summary-label">Data Acquisto:</span>
                        <span class="summary-value"><%= order.getOrderDate() %></span>
                    </div>
                    <div class="summary-item">
                        <span class="summary-label">Metodo Pagamento:</span>
                        <span class="summary-value"><%= order.getPaymentMethod() %></span>
                    </div>
                </div>
            </div>

            <div class="table-container">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>ID Libro</th>
                            <th>Nome Libro</th>
                            <th>Quantità</th>
                            <th>Prezzo Unitario</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(DetailOrderBean detail : details) { %>
                        <tr>
                            <td>#<%= detail.getBookId() %></td>
                            <td class="book-name"><%= detail.getBook().getName() %></td>
                            <td class="qty-cell"><%= detail.getQuantity()%></td>
                            <td>€ <%= String.format("%.2f", detail.getPriceAtPurchase()) %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                
                <div class="order-total-section">
                    <span>Totale Ordine:</span>
                    <span class="total-price">€ <%= String.format("%.2f", order.getTotal()) %></span>
                </div>
            </div>

        </main>
    </div>

</body>
</html>