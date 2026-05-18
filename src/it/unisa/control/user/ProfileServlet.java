package it.unisa.control.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.PurchaseOrderBean;
import it.unisa.model.UserBean;
import it.unisa.storage.order.PurchaseOrderDao;
import it.unisa.storage.order.PurchaseOrderDaoImpl;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseOrderDao dao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new PurchaseOrderDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String profileForm = request.getParameter("profile");
		RequestDispatcher dispatcher;
		
		switch(profileForm) {
			case "info":
				dispatcher = request.getRequestDispatcher("WEB-INF/views/user/profile.jsp");
				dispatcher.forward(request, response);
				break;
			case "orders":
				UserBean user = (UserBean) request.getSession().getAttribute("user");
				try {
					ArrayList<PurchaseOrderBean> orders = dao.doRetriveByUser(user.getId());
					request.setAttribute("orders", orders);
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
