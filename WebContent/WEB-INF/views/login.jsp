<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">
<title>OwlBooks - Login</title>
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

<div class="login-container">
	<div class="login-card">
	    
	    <div class="login-header">
	        <h2>Bentornato!</h2>
	        <p>Accedi a OwlBooks per continuare.</p>
	    </div>
	
	    <form action="<%= request.getContextPath() %>/LoginServlet" method="post" class="login-form">
	        
	        <div class="form-group">
	            <label for="email">Email</label>
	            <input type="email" id="email" name="email" required placeholder="mario.rossi@email.com">
	        </div>
	        
	        <div class="form-group">
	            <label for="password">Password</label>
	            <input type="password" id="password" name="password" required placeholder="Inserisci la password">
	        </div>
	        
	        <button type="submit" class="btn-submit">Accedi</button>
	        
	    </form>
	
	    <div class="login-footer">
	        <p>Non hai un account? <a href="<%= request.getContextPath() %>/SignInServlet">Registrati qui</a></p>
	    </div>
	
	</div>
</div>

</body>
</html>