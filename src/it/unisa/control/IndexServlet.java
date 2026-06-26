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
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
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
		ArrayList<String> genres = null;
		ArrayList<BookBean> catalogue = null;
		String searchQuery = request.getParameter("searchQuery");
		String pageString = request.getParameter("page") != null ? request.getParameter("page") : "1";
		int currentPage;
		int totalPages = 1;
		
		try {
			currentPage = Integer.parseInt(pageString);
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		try {
			genres = dao.doRetriveGenres();
			
			String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "az";
			String genreFilter = request.getParameter("filter");
			int totalBooks = 0;			
			
			if(searchQuery != null && !searchQuery.trim().isEmpty()) {
				catalogue = dao.doRetrieveByString(searchQuery, sort, currentPage-1);
				totalBooks = dao.doCountAll(searchQuery);
			} else if(genres.contains(genreFilter)){
					catalogue = dao.doRetriveAll("genre", genreFilter, sort, currentPage-1);
					totalBooks = dao.doCountAll("genre", genreFilter);
			} else {
					catalogue = dao.doRetriveAll(sort, true, currentPage - 1);
					totalBooks = dao.doCountAll();
			}
			
			if (catalogue == null) {
				catalogue = new ArrayList<>();
			}
			
			totalPages = (int) Math.ceil((double) totalBooks / 10);
			if(totalPages == 0)
				totalPages = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			catalogue = new ArrayList<>();
			if (genres == null) genres = new ArrayList<>();
		} 
		
		request.setAttribute("catalogue", catalogue);
		request.setAttribute("genres", genres);
		request.setAttribute("searchQuery", request.getParameter("searchQuery"));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
