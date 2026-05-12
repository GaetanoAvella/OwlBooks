package it.unisa.storage.book.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.BookBean;

public interface BookDao {
	public void doSave(BookBean book) throws SQLException;
	public boolean doDelete(String code) throws SQLException;
	public BookBean doRetriveByKey(String code) throws SQLException;
	public Collection<BookBean> doRetriveAll() throws SQLException;
}
