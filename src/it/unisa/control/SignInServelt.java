package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.unisa.model.UserBean;

@WebServlet("/SignInServelt")
public class SignInServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = new UserBean();
		
		user.setName(request.getParameter("name"));
		user.setName(request.getParameter("surname"));
		user.setName(request.getParameter("address"));
		user.setName(request.getParameter("email"));
		user.setName(request.getParameter("password"));
		
		
		
	}

}
