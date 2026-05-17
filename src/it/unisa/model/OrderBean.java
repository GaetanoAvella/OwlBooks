package it.unisa.model;

import java.sql.Date;
import java.util.Random;

public class OrderBean {
	private UserBean user;
	private CartBean cart;
	private String code;
	private Date date;
	private String paymentMethod;

	public OrderBean() {
		Random rand = new Random();
		
		code = user.getName().substring(0,4).toLowerCase() 
				+ user.getSurname().substring(0,4).toLowerCase()
		  		+ rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) 
		  		+ rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
	}
	
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public CartBean getCart() {
		return cart;
	}
	public void setCart(CartBean cart) {
		this.cart = cart;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}
