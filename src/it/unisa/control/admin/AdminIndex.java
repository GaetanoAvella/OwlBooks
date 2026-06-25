package it.unisa.control.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.BookBean;
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/admin/AdminIndex")
public class AdminIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao dao;
	
	@Override
	public void init() throws ServletException {
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new BookDaoImpl(ds);
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BookBean> catalogue = null;
		
		try {
			catalogue = dao.doRetrieveAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("catalogue", catalogue);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin_index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
