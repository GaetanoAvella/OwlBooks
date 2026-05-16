package it.unisa.control.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import it.unisa.model.KartBean;
import it.unisa.model.OrderBean;
import it.unisa.model.UserBean;

@WebServlet("/SummaryServlet")
public class SummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/user/summary.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		OrderBean order = new OrderBean();
		order.setUser((UserBean) session.getAttribute("user"));
		order.setKart((KartBean) session.getAttribute("kart"));
		order.setPaymentMethod(request.getParameter("payment_method"));
	}

}
