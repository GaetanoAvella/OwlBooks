<%@page import="it.unisa.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/global.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user/profile.css">
<title>OwlBooks - Profilo</title>
</head>
<body>

    <%@ include file="../header.jsp" %>

    <div class="profile-wrapper">
        <div class="profile-card">
            <% UserBean user = (UserBean) session.getAttribute("user"); %>
            
            <div class="profile-header">
                <div class="profile-avatar">
                    👤
                </div>
                <h1><%= user.getName() %> <%= user.getSurname() %></h1>
            </div>
            
            <div class="profile-details">
                <div class="info-group">
                    <span class="info-label">Indirizzo</span>
                    <span class="info-value"><%= user.getAddress() %></span>
                </div>
                <div class="info-group">
                    <span class="info-label">Email</span>
                    <span class="info-value"><%= user.getEmail() %></span>
                </div>
            </div>
            
            <div class="profile-actions">
                <a href="ProfileServlet?action=edit" class="btn-edit">✏️ Modifica Profilo</a>
            </div>
        </div>
    </div>

</body>
</html>
</html>