package it.unisa.storage.bookshelf.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.BookBean;

public interface BookshelfDao {
	public void doSave(BookBean book) throws SQLException;
	public boolean doDelete(BookBean book) throws SQLException;
	public BookBean doRetriveByKey(int code) throws SQLException;
	public Collection<BookBean> doRetriveAll() throws SQLException;
}
