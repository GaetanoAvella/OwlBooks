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

import it.unisa.model.KartBean;
import it.unisa.model.KartItem;
import it.unisa.storage.book.dao.BookDao;
import it.unisa.storage.book.dao.BookDaoImpl;

@WebServlet("/KartServlet")
public class KartServlet extends HttpServlet {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/kart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		KartBean kart = (KartBean) session.getAttribute("kart") == null ? new KartBean() : (KartBean) session.getAttribute("kart");
		String code = request.getParameter("code");
		KartItem item = null;
		try {
			item = new KartItem(dao.doRetriveByKey(code));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		kart.addItem(item);
		session.setAttribute("kart", kart);
		request.removeAttribute("code");
		
		doGet(request, response);
	}

}
