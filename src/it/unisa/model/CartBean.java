package it.unisa.model;

import java.util.ArrayList;

public class CartBean {
	private ArrayList<CartItem> cart;
	
	public CartBean() {
		cart = new ArrayList<CartItem>();
	}
	
	public void addItem(CartItem item) {
		if(item == null)
			return;
		
		for(CartItem x : cart) {
			if(x.getBook().getCode().equals(item.getBook().getCode())) {
				x.setQuantity(x.getQuantity() + 1);
				return;
			}
		}	
		
		cart.add(item);
	}
	
	public int sizeArrayList() {
		return cart.size();
	}
	
	public CartItem get(int index) {
		return cart.get(index);
	}
	
	public float getTotal() {
		float total = 0;
		
		for(CartItem item : cart)
			total += item.subTotal();
			
		return total;
	}
}
