package it.unisa.model;

public class DetailOrderBean {
	private int orderId;
	private int quantity;
	private float priceAtPurchase;
	private BookBean book;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPriceAtPurchase() {
		return priceAtPurchase;
	}
	public void setPriceAtPurchase(float priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}
	public BookBean getBook() {
		return book;
	}
	public void setBook(BookBean book) {
		this.book = book;
	}
	
	
	
}
