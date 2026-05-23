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
	    private DataSource ds;
		
	    public BookDaoImpl(DataSource ds) {
	    	this.ds = ds;
	    }
	    
		@Override
		public void doSave(BookBean book) throws SQLException {
			String insertSQL = "INSERT INTO " + TABLE_NAME
					+ " (code, name, author, genre, price, description, stock_quantity, editor) VALUES (?,?,?,?,?,?,?,?);";
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(insertSQL)) {
				statement.setString(1, book.getCode());
				statement.setString(2, book.getName());
				statement.setString(3, book.getAuthor());
				statement.setString(4, book.getGenre());
				statement.setFloat(5, book.getPrice());
				statement.setString(6, book.getDescription());
				statement.setInt(7, book.getStock_quantity());
				statement.setString(8, book.getEditor());
				statement.execute();
			}
		}
		
		@Override
		public boolean doDelete(String code) throws SQLException {
			String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE code = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
	            preparedStatement.setString(1, code);
	            int result = preparedStatement.executeUpdate();
	            return result != 0;
	        }
		}
		
		@Override
		public BookBean doRetriveByCode(String code) throws SQLException {
			BookBean book = new BookBean();
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE code=?";
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectSQL)) {
			
				statement.setString(1, code);
				try(ResultSet rs = statement.executeQuery()) {
					if(rs.next()) {
						book.setId(rs.getInt("id"));
						book.setCode(rs.getString("code"));
						book.setName(rs.getString("name"));
						book.setAuthor(rs.getString("author"));
						book.setGenre(rs.getString("genre"));
						book.setPrice(rs.getFloat("price"));
						book.setDescription(rs.getString("description"));
						book.setStock_quantity(rs.getInt("stock_quantity"));
						book.setEditor(rs.getString("editor"));
						book.setPath(rs.getString("path"));
						book.setMimeType(rs.getString("mime_type"));
					}
				}
			}
			
			return book;		
		}
		
		@Override
		public ArrayList<BookBean> doRetriveAll(String order) throws SQLException {
			ArrayList<BookBean> list = new ArrayList<>();
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE stock_quantity > 0 " + setOrderString(order) ;
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectSQL);
					ResultSet rs = statement.executeQuery()) {
				while(rs.next()) {
					BookBean book = new BookBean();
					book.setId(rs.getInt("id"));
					book.setCode(rs.getString("code"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setGenre(rs.getString("genre"));
					book.setPrice(rs.getFloat("price"));
					book.setDescription(rs.getString("description"));
					book.setStock_quantity(rs.getInt("stock_quantity"));
					book.setEditor(rs.getString("editor"));
					book.setPath(rs.getString("path"));
					book.setMimeType(rs.getString("mime_type"));
					list.add(book);
				}
			}
			return list;
		}
	
		@Override
		public ArrayList<BookBean> doRetriveAll(String filter, String filterValue, String order) throws SQLException {
			if(filterValue == null || filterValue.equals(""))
				return doRetriveAll(order);
			
			ArrayList<BookBean> list = new ArrayList<>();
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE " + filter + "=?" + setOrderString(order);
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectSQL)) {
				statement.setString(1, filterValue);
				try(ResultSet rs = statement.executeQuery()) {
					while(rs.next()) {
						BookBean book = new BookBean();
						book.setId(rs.getInt("id"));
						book.setCode(rs.getString("code"));
						book.setName(rs.getString("name"));
						book.setAuthor(rs.getString("author"));
						book.setGenre(rs.getString("genre"));
						book.setPrice(rs.getFloat("price"));
						book.setDescription(rs.getString("description"));
						book.setStock_quantity(rs.getInt("stock_quantity"));
						book.setEditor(rs.getString("editor"));
						book.setPath(rs.getString("path"));
						book.setMimeType(rs.getString("mime_type"));
						list.add(book);
					}
				}
			}
			return list;
		}
		
		@Override
		public ArrayList<String> doRetriveGenres() throws SQLException{
			ArrayList<String> genres = new ArrayList<String>();
			String selectSQL = "SELECT DISTINCT genre FROM " + TABLE_NAME + " ORDER BY genre ASC";
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectSQL);
					ResultSet rs = statement.executeQuery()) {
				
				while(rs.next()) {
					genres.add(rs.getString("genre"));
				}
			
			}
			
			return genres;
		}
		
		@Override
		public void doUpdate(BookBean book) throws SQLException {
			String updateSQL = "UPDATE " + TABLE_NAME + 
	                 " SET name=?, author=?, genre=?, price=?, description=?, stock_quantity=?, editor=? " +
	                 " WHERE code=?";
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(updateSQL)) {
				statement.setString(1, book.getName());
				statement.setString(2, book.getAuthor());
				statement.setString(3, book.getGenre());
				statement.setFloat(4, book.getPrice());
				statement.setString(5, book.getDescription());
				statement.setInt(6, book.getStock_quantity());
				statement.setString(7, book.getEditor());
				statement.setString(8, book.getCode());
				statement.executeUpdate();
			}
		}
		
		public String setOrderString(String order) {
			switch(order) {
				case "az":
					return " ORDER BY name ASC";
				case "za":
					return " ORDER BY name DESC";
				case "pricelow":
					return " ORDER BY price ASC";
				case "pricehigh":
					return " ORDER BY price DESC";
				default:
					return " ORDER BY name ASC";
			}
		}
	
	}
