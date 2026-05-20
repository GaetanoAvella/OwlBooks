package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.storage.user.dao.UserDao;
import it.unisa.storage.user.dao.UserDaoImpl;
import it.unisa.util.PasswordDigest;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao dao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new UserDaoImpl(ds);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		try {
			if(dao.isRegistered(email)) {
				if(dao.checkPassword(email, PasswordDigest.digestPassword(request.getParameter("password")))) {
					HttpSession session = request.getSession();
					session.setAttribute("user", dao.doRetriveByKey(email));
					response.sendRedirect("IndexServlet");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
