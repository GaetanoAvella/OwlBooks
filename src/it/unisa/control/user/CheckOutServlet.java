package it.unisa.control.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.model.CartBean;
import it.unisa.model.PurchaseOrderBean;
import it.unisa.model.UserBean;
import it.unisa.storage.order.PurchaseOrderDao;
import it.unisa.storage.order.PurchaseOrderDaoImpl;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/user/checkout.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		CartBean cart = (CartBean) session.getAttribute("cart");
		String paymentMethod = request.getParameter("payment_method");
		
		PurchaseOrderBean order = new PurchaseOrderBean();
		order.setUserId(user.getId());
		order.setOrderDate(new Date(System.currentTimeMillis()));
		order.setPaymentMethod(paymentMethod);
		order.setTotal(cart.getTotal());
		order.setDetails(cart);
		
		try {
			dao.doSave(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cart.clear();
		session.setAttribute("cart", cart);
		response.sendRedirect("IndexServlet");
	}

}
