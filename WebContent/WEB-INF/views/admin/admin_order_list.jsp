<%@page import="it.unisa.model.PurchaseOrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>OwlBooks - Gestione Ordini</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_order_list.css">
</head>
<body>

    <%@ include file="../error.jsp" %>

    <div class="admin-dashboard">
        
        <%@ include file="admin_global.jsp" %>

        <main class="admin-content">
            <div class="content-header">
                <h1>📦 Gestione Ordini</h1>
            </div>
            
            <div class="filter-card">
                <form action="<%= request.getContextPath() %>/admin/AdminOrderList" method="post" class="filter-form">
                    <div class="date-inputs">
                        <div class="input-group">
                            <label for="from">Da:</label>
                            <input type="date" id="from" name="from" value="<%= request.getAttribute("from_date") != null ? request.getAttribute("from_date") : "" %>">
                        </div>
                        <div class="input-group">
                            <label for="to">A:</label>
                            <input type="date" id="to" name="to" value="<%= request.getAttribute("to_date") != null ? request.getAttribute("to_date") : "" %>">
                        </div>
                    </div>
                    <button type="submit" class="btn-search">🔍 Cerca Ordini</button>
                </form>
            </div>

            <% ArrayList<PurchaseOrderBean> orders = (ArrayList<PurchaseOrderBean>) request.getAttribute("orders"); %>

            <div class="table-container">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>Id Ordine</th>
                            <th>Id Utente</th>
                            <th>Codice Ordine</th>
                            <th>Data Acquisto</th>
                            <th>Metodo Pagamento</th>
                            <th>Totale</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% if(orders != null && !orders.isEmpty()) {
                        for(PurchaseOrderBean order : orders) { %>
                        <tr>
                            <td>#<%= order.getId() %></td>
                            <td><%= order.getUserId() %></td>
                            <td><a href="<%= request.getContextPath() %>/admin/AdminOrderDetail?code=<%= order.getOrderCode() %>" class="code-link"><%= order.getOrderCode() %></a></td>
                            <td><%= order.getOrderDate() %></td>
                            <td><%= order.getPaymentMethod(true) %></td>
                            <td class="price-cell">€ <%= String.format("%.2f", order.getTotal()) %></td>
                        </tr>
                    <%  } 
                    } else { %>
                        <tr>
                            <td colspan="6" style="text-align: center; padding: 30px; color: #7f8c8d;">Nessun ordine trovato in questo intervallo di date.</td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

        </main>
    </div>

</body>
</html>