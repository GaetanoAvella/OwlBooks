package it.unisa.model;

public class KartItem {
	private BookBean book;
	private int quantity;
	
	public KartItem(BookBean book) {
		this.book = book;
		quantity = 1;
	}
	
	public float subTotal() {
		return book.getPrice() * quantity;
	}

	public BookBean getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
