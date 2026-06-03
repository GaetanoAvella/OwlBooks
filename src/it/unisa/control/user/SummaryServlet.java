package it.unisa.control.user;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.model.PurchaseOrderBean;
import it.unisa.storage.order.dao.PurchaseOrderDao;
import it.unisa.storage.order.dao.PurchaseOrderDaoImpl;

@WebServlet("/user/SummaryServlet")
public class SummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseOrderDao dao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new PurchaseOrderDaoImpl(ds);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code") != null ? request.getParameter("code") : "";
		
		try {
			PurchaseOrderBean order = dao.doRetriveByCode(code);
			if(order != null) {
				request.setAttribute("order", order);
				request.getRequestDispatcher("/WEB-INF/views/user/summary.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/user/OrdersServlet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
