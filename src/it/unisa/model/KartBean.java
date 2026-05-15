package it.unisa.model;

import java.util.ArrayList;

public class KartBean {
	private ArrayList<KartItem> kart;
	
	public KartBean() {
		kart = new ArrayList<KartItem>();
	}
	
	public void addItem(KartItem item) {
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
	
	public KartItem get(int index) {
		return kart.get(index);
	}
	
	public float getTotal() {
		float total = 0;
		
		for(KartItem item : kart)
			total += item.subTotal();
			
		return total;
	}
}
