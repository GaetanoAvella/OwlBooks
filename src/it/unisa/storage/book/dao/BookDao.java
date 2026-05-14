package it.unisa.storage.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import it.unisa.model.BookBean;

public interface BookDao {
	public void doSave(BookBean book) throws SQLException;
	public boolean doDelete(String code) throws SQLException;
	public BookBean doRetriveByKey(String code) throws SQLException;
	public ArrayList<BookBean> doRetriveAll(String order) throws SQLException;
	public ArrayList<String> doRetriveAllGenre() throws SQLException;
	public ArrayList<BookBean> doRetriveAllbyGenre(String genre) throws SQLException;
}
