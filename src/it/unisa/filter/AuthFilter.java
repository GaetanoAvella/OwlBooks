package it.unisa.filter;

import java.io.IOException;

import it.unisa.model.UserBean;
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
		UserBean user = session != null ? (UserBean) session.getAttribute("user") : null;
		String path = request.getRequestURI();
		
		if(path.contains("/admin")) {
			if(user != null && user.isAdmin())
				chain.doFilter(request, response);
			else
				response.sendRedirect(request.getContextPath() + "/IndexServlet");
		} else if(path.contains("/user")) {
			if(user != null)
				chain.doFilter(request, response);
			else
				response.sendRedirect(request.getContextPath() + "/LoginServlet");
		}
	}
}
