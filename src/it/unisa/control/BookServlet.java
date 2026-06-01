package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.model.BookBean;
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
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
		String code = request.getParameter("code");
		BookBean book = null;
		
		try {
			book = dao.doRetriveByCode(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(book == null) {
			response.sendRedirect(request.getContextPath() + "/IndexServlet");
			return;
		}
		
		request.setAttribute("book", book);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/book.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
