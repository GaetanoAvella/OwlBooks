package it.unisa.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserBean {
	private int id;
	private String name;
	private String surname;
	private String address;
	private String email;
	private String password;
	private boolean admin;
	private byte[] pic;
	private String mimeType;
	
	public UserBean() {
		FileInputStream stream;
		try {
			stream = new FileInputStream("img/generic_user.jpg");
			pic = stream.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
