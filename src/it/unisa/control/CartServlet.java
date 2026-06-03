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

import it.unisa.model.BookBean;
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
		String action = request.getParameter("action");
		CartItem item;
		
		switch(action) {
			case "add":
				try {
					BookBean book = dao.doRetriveByCode(code);
					if(cart.get(code) != null && cart.get(code).getQuantity() + 1 > book.getStock_quantity()) {
						request.setAttribute("book", book);
						request.setAttribute("error", "Impossibile aggiungere questo libro nel carrello!");
						request.getRequestDispatcher("/WEB-INF/views/book.jsp").forward(request, response);
						return;
					}
					item = new CartItem(book);
					cart.addItem(item);
					session.setAttribute("cart", cart);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				break;
			case "update":
				item = cart.get(code);
				String quantityString = request.getParameter("quantity") != null && !request.getParameter("quantity").isEmpty() 
						? request.getParameter("quantity") : "1";
				try {
					int quantity = Integer.parseInt(quantityString);
					BookBean book = dao.doRetriveByCode(code);
					
					if(quantity > book.getStock_quantity())
						quantity = book.getStock_quantity();
					
					item.setQuantity(quantity);
					session.setAttribute("cart", cart);
				} catch(NumberFormatException e) {
					request.setAttribute("error", "Valore inserito non valido");
					request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "remove":
				cart.removeItem(code);
				session.setAttribute("cart", cart);
				break;
			case "clear":
				cart.clear();
				session.setAttribute("cart", cart);
				break;
		}
		
		doGet(request, response);
	}

}
