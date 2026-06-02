package it.unisa.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpsFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(!request.isSecure()) {
			String url = request.getRequestURL().toString();
			String secureUrl = url.replace("http", "https");
			String queryString = request.getQueryString();
			if(queryString != null)
				secureUrl += "?" + queryString;
			
			response.sendRedirect(secureUrl);
			return;
		}
		
		chain.doFilter(request, response);
	}
}
