<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.model.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>OwlBooks - Home</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
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

    <div class="main-container">

        <aside class="sidebar">
            
            <% String currentSort = request.getParameter("sort") != null ? request.getParameter("sort") : "az"; %>
            
            <div class="filter-section">
                <h3>Ordinamento</h3>
                <form method="get" action="IndexServlet" class="sort-form">
                    <label for="sort">Ordina i libri per: </label>
                    <select name="sort" id="sort">
                        <option value="az" <%= currentSort.equals("az") ? "selected" : "" %>>Alfabetico (A-Z)</option>
                        <option value="za" <%= currentSort.equals("za") ? "selected" : "" %>>Alfabetico (Z-A)</option>
                        <option value="pricelow" <%= currentSort.equals("pricelow") ? "selected" : "" %>>Dal meno costoso</option>
                        <option value="pricehigh" <%= currentSort.equals("pricehigh") ? "selected" : "" %>>Dal più costoso</option>
                    </select>
                    
                    <% if(request.getParameter("filter") != null) { %>
                    <input type="hidden" name="filter" value="<%= request.getParameter("filter") %>">
                    <% } %>
                    
                    <input type="submit" value="Ordina" class="btn-sort">
                </form>
            </div>

            <div class="genres-section">
                <h3>Generi</h3>
                <ul>
                    <li><a href="IndexServlet?sort=<%= currentSort %>" class="<%= request.getParameter("filter") == null ? "active" : "" %>">Mostra tutti</a></li>
                
                <% 
                ArrayList<String> genres = (ArrayList<String>) request.getAttribute("genres");
                for (String genre : genres) {
                    // Controlla se questo genere è quello attualmente selezionato
                    boolean isSelected = genre.equals(request.getParameter("filter"));
                %>
                    <li>
                        <a href="IndexServlet?filter=<%= genre %>&sort=<%= currentSort %>" class="<%= isSelected ? "active" : "" %>">
                            <%= genre %>
                        </a>
                    </li>
                <% } %>
                </ul>
            </div>
            
        </aside>

        <main class="catalogue-section">
            <h2>Catalogo Libri</h2>
            
            <table class="book-table">
                <thead>
                    <tr>
                        <th>Titolo del Libro</th>
                        <th>Autore</th>
                        <th>Prezzo</th> 
                    </tr>
                </thead>
                <tbody>
                <%
                ArrayList<BookBean> catalogue = (ArrayList<BookBean>) request.getAttribute("catalogue");
                for (BookBean book : catalogue) {
                %>
                    <tr>
                        <td class="title-cell"><a href="BookServlet?code=<%= book.getCode() %>">📘 <%= book.getName() %></a></td>
                        <td><%= book.getAuthor() %></td>
                        <td class="price-cell">€ <%= String.format("%.2f", book.getPrice()) %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </main>

    </div>

</body>
</html>