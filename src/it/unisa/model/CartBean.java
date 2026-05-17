package it.unisa.model;

import java.util.ArrayList;

public class CartBean {
	private ArrayList<CartItem> kart;
	
	public CartBean() {
		kart = new ArrayList<CartItem>();
	}
	
	public void addItem(CartItem item) {
		if(item == null)
			return;
		
		if(kart.contains(item)) {
			item.setQuantity(item.getQuantity() + 1);
		} else {
			kart.add(item);
		}
	}
	
	public int sizeArrayList() {
		return kart.size();
	}
	
	public CartItem get(int index) {
		return kart.get(index);
	}
	
	public float getTotal() {
		float total = 0;
		
		for(CartItem item : kart)
			total += item.subTotal();
			
		return total;
	}
}
