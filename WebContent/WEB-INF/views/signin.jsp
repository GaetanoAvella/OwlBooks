<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%= request.getContextPath() %>/scripts/validation_field.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/signin.css?v=1">
<title>OwlBooks - Registrazione</title>
</head>
<body>

<% 
if(session.getAttribute("user") != null)  {
	if(session.getAttribute("admin") != null){
		response.sendRedirect(request.getContextPath() + "/admin/AdminIndex");
		return;
	} else
		response.sendRedirect(request.getContextPath() + "/IndexServlet");
		return;
	}
%>
	
	<div class="signin-container">
	
        <div class="signin-card">
            
            <div class="signin-header">
                <h2>Crea un Account</h2>
                </div>

            <form action="<%= request.getContextPath() %>/SignInServlet" method="post" class="signin-form" id="reg-form" onsubmit="return validate()">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="name" required placeholder="Mario"
                        onchange="validateFormElem(this,nameAndSurnamePattern,document.getElementById('errorName'),errorNameMsg)">
                        <span id="errorName"></span>
                    </div>
                    
                    <div class="form-group">
                        <label for="cognome">Cognome</label>
                        <input type="text" id="cognome" name="surname" required placeholder="Rossi"
                        onchange="validateFormElem(this,nameAndSurnamePattern,document.getElementById('errorSurname'),errorSurnameMsg)">
                        <span id="errorSurname"></span>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="email">Indirizzo Email</label>
                    <input type="email" id="email" name="email" required placeholder="mario.rossi@email.com">
                    <% if(request.getAttribute("error") != null) { %>
                    	<span id="errorEmail"><%= request.getAttribute("error") %></span>
                    <% } %>
                </div>

                <div class="form-group">
                    <label for="indirizzo">Indirizzo</label>
                    <input type="text" id="indirizzo" name="address" required placeholder="Via Roma, 10, Napoli"
                    onchange="validateFormElem(this,addressPattern,document.getElementById('errorAddress'),errorAddressMsg)">
                    <span id="errorAddress"></span>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Scegli una password sicura">
                    <span id="errorPassword"></span>
                </div>
                
                <button type="submit" class="btn-submit">Registrati</button>
                
            </form>

            <div class="signin-footer">
                <p>Hai già un account? <a href="<%= request.getContextPath() %>/LoginServlet">Accedi qui</a></p>
            </div>

        </div>
    </div>
    
</body>
</html>