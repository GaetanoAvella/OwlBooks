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

import it.unisa.model.UserBean;
import it.unisa.storage.book.dao.BookDaoImpl;
import it.unisa.storage.user.dao.UserDao;
import it.unisa.storage.user.dao.UserDaoImpl;

@WebServlet("/SignInServelt")
public class SignInServelt extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/signin.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(dao.isRegistered(request.getParameter("email")))
				throw new Exception("Utente già registrato");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserBean user = new UserBean();
		user.setName(request.getParameter("name"));
		user.setName(request.getParameter("surname"));
		user.setName(request.getParameter("address"));
		user.setName(request.getParameter("email"));
		user.setName(request.getParameter("password"));
		
		try {
			dao.doSave(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		response.sendRedirect("IndexServlet");
	}

}
