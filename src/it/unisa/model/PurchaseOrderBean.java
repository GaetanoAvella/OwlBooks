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
		
		details = new ArrayList<DetailOrderBean>();
	}
	
	public PurchaseOrderBean() {
		Random rand = new Random();
		
		orderCode = "ORD-" +  System.currentTimeMillis()
		  		+ rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) ;
		
		details = new ArrayList<DetailOrderBean>();
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

	public void setDetails(CartBean cart) {
		for(int i=0; i<cart.sizeArrayList(); i++) {
			DetailOrderBean detail = new DetailOrderBean();
			CartItem item = cart.get(i);
			detail.setBookId(item.getBook().getId());
			detail.setQuantity(item.getQuantity());
			detail.setPriceAtPurchase(item.getBook().getPrice());
			detail.setBook(item.getBook());
			details.add(detail);
		}
	}
	
}
