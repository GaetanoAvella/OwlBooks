package it.unisa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

public class PurchaseOrderBean {
	private String orderCode;
	private String userId;
	private Date orderDate;
	private String paymentMethod;
	private float total;
	private ArrayList<DetailOrderBean> details;

	public PurchaseOrderBean(String userId) {
		this.userId = userId;
		
		Random rand = new Random();
		
		orderCode = userId
		  		+ rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) 
		  		+ rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public ArrayList<DetailOrderBean> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<DetailOrderBean> details) {
		this.details = details;
	}
	
}
