package it.unisa.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		boolean isLogged = session != null && session.getAttribute("user") != null ? true : false;
		
		if(isLogged)
			chain.doFilter(request, response);
		else
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
	}
}
