package it.unisa.storage.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import it.unisa.model.BookBean;

public class BookDaoImpl implements BookDao{
	private static final String TABLE_NAME = "book";
    private DataSource ds = null;
	
    public BookDaoImpl(DataSource ds) {
    	this.ds = ds;
    }
    
	@Override
	public synchronized void doSave(BookBean book) throws SQLException {
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (name, author, genre, description, price, quantity) VALUES (?,?,?,?,?,?);";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertSQL)) {
			statement.setString(1, book.getNome());
			statement.setString(2, book.getAutore());
			statement.setString(3, book.getGenere());
			statement.setString(4, book.getDescrizione());
			statement.setFloat(5, book.getPrezzo());
			statement.setInt(6, book.getQuantita());
			statement.execute();
		}
	}
	
	@Override
	public boolean doDelete(int code) throws SQLException {
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE code = ?";
        try (Connection connection = ds.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, code);
            int result = preparedStatement.executeUpdate();
            return result != 0;
        }
	}
	
	@Override
	public synchronized BookBean doRetriveByKey(int code) throws SQLException {
			BookBean book = new BookBean();
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE code=?";
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectSQL)) {
			
				statement.setInt(1, code);
				try(ResultSet rs = statement.executeQuery()) {
					book.setCodice(rs.getInt("code"));
					book.setNome(rs.getString("name"));
					book.setAutore(rs.getString("author"));
					book.setGenere(rs.getString("genre"));
					book.setDescrizione(rs.getString("description"));
					book.setPrezzo(rs.getFloat("price"));
					book.setQuantita(rs.getInt("quantity"));
				}
			}
			
			return book;		
	}
	
	@Override
	public ArrayList<BookBean> doRetriveAll() throws SQLException {
		ArrayList<BookBean> list = new ArrayList<>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL);
				ResultSet rs = statement.executeQuery()) {
			while(rs.next()) {
				BookBean book = new BookBean();
				book.setCodice(rs.getInt("code"));
				book.setNome(rs.getString("name"));
				book.setAutore(rs.getString("author"));
				book.setGenere(rs.getString("genre"));
				book.setDescrizione(rs.getString("description"));
				book.setPrezzo(rs.getFloat("price"));
				book.setQuantita(rs.getInt("quantity"));
				list.add(book);
			}
		}
		return list;
	}
}
