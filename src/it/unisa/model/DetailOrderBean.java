package it.unisa.model;

public class DetailOrderBean {
	private int id;
	private int orderId;
	private int bookId;
	private int quantity;
	private double priceAtPurchase;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPriceAtPurchase() {
		return priceAtPurchase;
	}
	public void setPriceAtPurchase(double priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}
	
}
