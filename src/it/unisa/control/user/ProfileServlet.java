package it.unisa.control.user;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/user/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new UserDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		
		if(action.equals("edit")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/edit_profile.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/profile.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UserBean user = (UserBean) session.getAttribute("user");
		
		user.setName(request.getParameter("name"));
		user.setSurname(request.getParameter("surname"));
		user.setAddress(request.getParameter("address"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(PasswordDigest.digestPassword(request.getParameter("password")));
		
		try {
			dao.doUpdate(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
