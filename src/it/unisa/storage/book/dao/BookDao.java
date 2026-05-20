package it.unisa.storage.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import it.unisa.model.BookBean;

public interface BookDao {
	public void doSave(BookBean book) throws SQLException;
	public boolean doDelete(String code) throws SQLException;
	public BookBean doRetriveByCode(String code) throws SQLException;
	public ArrayList<BookBean> doRetriveAll(String order) throws SQLException;
	public ArrayList<BookBean> doRetriveAll(String filter, String filterValue, String order) throws SQLException;
	public ArrayList<String> doRetriveGenres() throws SQLException;
}
