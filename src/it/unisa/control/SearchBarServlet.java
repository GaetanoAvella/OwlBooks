package it.unisa.control;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.model.BookBean;
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/SearchBarServlet")
public class SearchBarServlet extends HttpServlet {
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
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		
		String query = request.getParameter("searchQuery");
		JSONArray jsonArray = new JSONArray();
		
		try {
			if(query != null && !query.trim().isEmpty()) {
				ArrayList<BookBean> results = dao.doRetrieveByString(query.trim(), "az", -1);
				
				if(results != null) {
					for(BookBean book : results) {
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("code", book.getCode());
						jsonObj.put("name", book.getName());
						jsonObj.put("author", book.getAuthor());
						jsonArray.put(jsonObj);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		writer.print(jsonArray.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
