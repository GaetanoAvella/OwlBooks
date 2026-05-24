package it.unisa.control.admin;

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

@WebServlet("/AdminOrderDetail")
public class AdminOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseOrderDao dao;
	
	@Override
	public void init() throws ServletException {
		DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
		if(ds == null)
			throw new ServletException("Datasource non disponibile nel contest");
		
		dao = new PurchaseOrderDaoImpl(ds);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		try {
			PurchaseOrderBean order = dao.doRetriveByCode(code);
			request.setAttribute("order", order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("WEB-INF/views/admin/admin_order_detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
