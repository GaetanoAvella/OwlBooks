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
import it.unisa.storage.user.dao.UserDao;
import it.unisa.storage.user.dao.UserDaoImpl;
import it.unisa.util.PasswordDigest;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signin.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(dao.isRegistered(request.getParameter("email"))) {
				request.setAttribute("error", "L'email è già registrata");
				doGet(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserBean user = new UserBean();
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setAddress(request.getParameter("address"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(PasswordDigest.digestPassword(request.getParameter("password")));
		
		try {
			dao.doSave(user);
			user = dao.doRetriveByKey(user.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		response.sendRedirect("IndexServlet");
	}

}
