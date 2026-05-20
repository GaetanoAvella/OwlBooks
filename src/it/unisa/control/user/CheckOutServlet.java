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

import it.unisa.model.BookBean;
import it.unisa.model.CartBean;
import it.unisa.model.CartItem;
import it.unisa.model.PurchaseOrderBean;
import it.unisa.model.UserBean;
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;
import it.unisa.storage.order.PurchaseOrderDao;
import it.unisa.storage.order.PurchaseOrderDaoImpl;

@WebServlet("/user/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseOrderDao orderDao;
	private BookDao bookDao; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		orderDao = new PurchaseOrderDaoImpl(ds);
		bookDao = new BookDaoImpl(ds);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/checkout.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		CartBean cart = (CartBean) session.getAttribute("cart");
		String paymentMethod = request.getParameter("payment_method");
		
		try {
			for(int i=0; i<cart.sizeArrayList(); i++) {
				CartItem item = cart.get(i);
				BookBean book = bookDao.doRetriveByCode(item.getBook().getCode());
				if(item.getQuantity() > book.getStock_quantity()) {
					request.setAttribute("error", "Quantità non disponibile per " + book.getName());
					doGet(request, response);
					return;
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PurchaseOrderBean order = new PurchaseOrderBean();
		order.setUserId(user.getId());
		order.setOrderDate(new Date(System.currentTimeMillis()));
		order.setPaymentMethod(paymentMethod);
		order.setTotal(cart.getTotal());
		order.setDetails(cart);
		
		try {
			orderDao.doSave(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cart.clear();
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath() + "/IndexServlet");
	}

}
