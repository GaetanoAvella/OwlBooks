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

import it.unisa.model.CartBean;
import it.unisa.model.CartItem;
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private BookDao dao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new BookDaoImpl(ds);
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		CartBean cart = (CartBean) session.getAttribute("cart") == null ? new CartBean() : (CartBean) session.getAttribute("cart");
		String code = request.getParameter("code");
		CartItem item = null;
		try {
			item = new CartItem(dao.doRetriveByKey(code));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cart.addItem(item);
		session.setAttribute("cart", cart);
		request.removeAttribute("code");
		
		doGet(request, response);
	}

}
