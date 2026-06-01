<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/error.css">

<%
String errorMsg = (String) request.getAttribute("error"); 
        
if(errorMsg != null) { %>
 
	 <div class="error-message">
	     ⚠️ <%= errorMsg %>
	</div>

<% } %>