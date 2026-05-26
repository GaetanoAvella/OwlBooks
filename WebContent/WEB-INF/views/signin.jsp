<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/signin.css">
<title>Registrazione</title>
</head>
<body>

<% 
if(session.getAttribute("user") != null)  {
	if(session.getAttribute("admin") != null)
		response.sendRedirect(request.getContextPath() + "/admin/AdminIndex");
	else
		response.sendRedirect(request.getContextPath() + "/IndexServlet");
}	
%>
	
	<div class="signin-container">
        <div class="signin-card">
            
            <div class="signin-header">
                <h2>Crea un Account</h2>
                </div>

            <form action="<%= request.getContextPath() %>/SignInServlet" method="post" class="signin-form">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="name" required placeholder="Mario">
                    </div>
                    
                    <div class="form-group">
                        <label for="cognome">Cognome</label>
                        <input type="text" id="cognome" name="surname" required placeholder="Rossi">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="email">Indirizzo Email</label>
                    <input type="email" id="email" name="email" required placeholder="mario.rossi@email.com">
                </div>

                <div class="form-group">
                    <label for="indirizzo">Indirizzo</label>
                    <input type="text" id="indirizzo" name="address" required placeholder="Via Roma, 10, Napoli">
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Scegli una password sicura">
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