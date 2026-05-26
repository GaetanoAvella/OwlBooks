package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.model.BookBean;
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/ImageBookServlet")
public class ImageBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
        dao = new BookDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		try {
			BookBean book = dao.doRetriveByCode(code);
			String path = book.getPath() != null && !book.getPath().isEmpty() ? book.getPath() : getServletContext().getRealPath("/img/book/default_book.png");
			String mimeType = book.getMimeType() != null && !book.getMimeType().isEmpty() ? book.getMimeType() : "image/png";
			
			response.setContentType(mimeType);
			try(InputStream in = new FileInputStream(path);
					OutputStream out = response.getOutputStream()) {
				in.transferTo(out);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
