package it.unisa.storage.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import it.unisa.model.BookBean;

public interface BookDao {
	public void doSave(BookBean book) throws SQLException;
	public void doSetActivate(String code, boolean isActive) throws SQLException;
	public BookBean doRetriveByCode(String code) throws SQLException;
	public ArrayList<BookBean> doRetriveAll(String order, boolean isActive, int page) throws SQLException;
	public ArrayList<BookBean> doRetriveAll(String filter, String filterValue, String order, int page) throws SQLException;
	public ArrayList<String> doRetriveGenres() throws SQLException;
	public void doUpdate(BookBean book) throws SQLException;
	public boolean isRegistered(String code) throws SQLException;
	public ArrayList<BookBean> doRetrieveByString(String query, String orderm, int page) throws SQLException;
	public int doCountAll() throws SQLException;
	public int doCountAll(String filter, String filterValue) throws SQLException;
	public int doCountAll(String query) throws SQLException;	
}
