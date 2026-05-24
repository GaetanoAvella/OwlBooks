package it.unisa.control.admin;

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

@WebServlet("/admin/AdminBook")
public class AdminBook extends HttpServlet {
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
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		String code = request.getParameter("code") != null ? request.getParameter("code") : "";
		
		switch(action) {
			case "add":
				request.getRequestDispatcher("/WEB-INF/views/admin/admin_new_book.jsp").forward(request, response);
				break;
			case "edit":
				try {
					BookBean book = dao.doRetriveByCode(code);
					request.setAttribute("book", book);
					request.getRequestDispatcher("/WEB-INF/views/admin/admin_edit_book.jsp").forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "delete":
				try {
					if(!dao.doDelete(code))
						request.setAttribute("error", "Eliminazione fallita");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath() + "/admin/AdminIndex");
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/admin/AdminIndex");
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code") != null ? request.getParameter("code") : "";
		String action = request.getParameter("action") ;
		
		if("add".equals(action)) {
			try {
				if(dao.isRegistered(request.getParameter("code"))) {
					request.setAttribute("error", "Il codice è già registrato");
					request.getRequestDispatcher("/WEB-INF/views/admin/admin_new_book.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
			BookBean book = new BookBean();
			
			book.setCode(request.getParameter("code"));
			book.setName(request.getParameter("name"));
			book.setAuthor(request.getParameter("author"));
			book.setEditor(request.getParameter("editor"));
			book.setGenre(request.getParameter("genre"));
			book.setDescription(request.getParameter("description"));
			book.setPrice(Float.parseFloat(request.getParameter("price")));
			book.setStock_quantity(Integer.parseInt(request.getParameter("quantity")));
			
			try {
				dao.doSave(book);
				book = dao.doRetriveByCode(book.getCode());
				response.sendRedirect(request.getContextPath() + "/admin/AdminIndex");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if("edit".equals(action)){
			try {
				BookBean book = dao.doRetriveByCode(code);
				
				book.setName(request.getParameter("name"));
				book.setAuthor(request.getParameter("author"));
				book.setEditor(request.getParameter("editor"));
				book.setGenre(request.getParameter("genre"));
				book.setDescription(request.getParameter("description"));
				book.setPrice(Float.parseFloat(request.getParameter("price")));
				book.setStock_quantity(Integer.parseInt(request.getParameter("quantity")));
				
				dao.doUpdate(book);
				response.sendRedirect(request.getContextPath() + "/BookServlet?code=" + book.getCode());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/AdminIndex");
	}

}
