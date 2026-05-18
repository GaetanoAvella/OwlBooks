package it.unisa.control.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String profileForm = request.getParameter("profile");
		RequestDispatcher dispatcher;
		
		switch(profileForm) {
			case "info":
				dispatcher = request.getRequestDispatcher("WEB-INF/views/user/profile.jsp");
				dispatcher.forward(request, response);
				break;
			case "orders":
				dispatcher = request.getRequestDispatcher("WEB-INF/views/user/orders.jsp");
				dispatcher.forward(request, response);
				break;
			case "logout":
				request.getSession().invalidate();
				break;
			default:
				dispatcher = request.getRequestDispatcher("WEB-INF/views/user/profile.jsp");
				dispatcher.forward(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
