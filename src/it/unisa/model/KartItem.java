package it.unisa.model;

public class KartItem {
	private BookBean book;
	private int quantity;
	
	public KartItem(BookBean book) {
		this.book = book;
		quantity = 0;
	}
	
	public float subTotal() {
		return book.getPrice() * quantity;
	}
}
