package it.unisa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

public class PurchaseOrderBean {
	private int id;
	private int userId;
	private String orderCode;
	private Date orderDate;
	private String paymentMethod;
	private double total;
	private ArrayList<DetailOrderBean> details;

	public PurchaseOrderBean(boolean withCode) {
		if(withCode) {
			Random rand = new Random();
			
			orderCode = "ORD-" +  System.currentTimeMillis()
			  		+ rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) ;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList<DetailOrderBean> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<DetailOrderBean> details) {
		this.details = details;
	}
	
}
