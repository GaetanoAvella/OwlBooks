package it.unisa.control.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.PurchaseOrderBean;
import it.unisa.storage.order.dao.PurchaseOrderDao;
import it.unisa.storage.order.dao.PurchaseOrderDaoImpl;

@WebServlet("/admin/AdminOrderList")
public class AdminOrderList extends HttpServlet {
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
		Date to = new Date(System.currentTimeMillis());
		Date from = new Date(System.currentTimeMillis());
		
		try {
			ArrayList<PurchaseOrderBean> orders = dao.doRetrieveAll();
			request.setAttribute("orders", orders);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("from_date", from);
		request.setAttribute("to_date", to);
		request.getRequestDispatcher("/WEB-INF/views/admin/admin_order_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromString = request.getParameter("from");
		String toString = request.getParameter("to");
	
		if(fromString != null && !fromString.isEmpty() && toString != null && !toString.isEmpty()) {
			Date from = Date.valueOf(fromString);
			Date to = Date.valueOf(toString);
			
			try {
				ArrayList<PurchaseOrderBean> orders = dao.doRetrieveAllbyTime(from, to);
				request.setAttribute("orders", orders);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("from_date", from);
			request.setAttribute("to_date", to);
		} else {
			request.setAttribute("error", "Inserisci entrambe le date");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/admin/admin_order_list.jsp").forward(request, response);
	}

}
