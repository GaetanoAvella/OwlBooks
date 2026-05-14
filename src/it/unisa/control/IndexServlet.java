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
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.BookBean;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDaoImpl dao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new BookDaoImpl(ds);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> genres = null;
		ArrayList<BookBean> catalogue = null;
		
		try {
			String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "a";
			String genreFilter = request.getParameter("filter");
			
			catalogue = dao.doRetriveAll("genre", genreFilter, sort);
			genres = dao.doRetriveGenres();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("catalogue", catalogue);
		request.setAttribute("genres", genres);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
