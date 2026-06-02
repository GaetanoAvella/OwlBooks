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
					+ " (code, name, author, genre, price, description, stock_quantity, editor, is_active, path, mime_type) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
			
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
				statement.setBoolean(9, book.isActive());
				statement.setString(10, book.getPath());
				statement.setString(11, book.getMimeType());
				statement.execute();
			}
		}
		
		@Override
		public void doSetActivate(String code, boolean active) throws SQLException {
			String updateSQL = "UPDATE " + TABLE_NAME + " SET is_active=? WHERE code = ?";
	        try (Connection connection = ds.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
	        	preparedStatement.setBoolean(1, active);
	            preparedStatement.setString(2, code);
	            preparedStatement.executeUpdate();
	        }
		}
		
		@Override
		public BookBean doRetriveByCode(String code) throws SQLException {
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE code=?";
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectSQL)) {
			
				statement.setString(1, code);
				try(ResultSet rs = statement.executeQuery()) {
					if(rs.next()) {
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
						book.setActive(rs.getBoolean("is_active"));
						book.setPath(rs.getString("path"));
						book.setMimeType(rs.getString("mime_type"));
						
						return book;
					}
				}
			}
			
			return null;		
		}
		
		@Override
		public ArrayList<BookBean> doRetriveAll(String order, boolean isActive) throws SQLException {
			ArrayList<BookBean> list = new ArrayList<>();
			String selectSQL = null;
			
			if(isActive) {
				selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE is_active=true" + setOrderString(order);
			} else {
				selectSQL = "SELECT * FROM " + TABLE_NAME + setOrderString(order);
			}
			
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
					book.setActive(rs.getBoolean("is_active"));
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
				return doRetriveAll(order, false);
			
			ArrayList<BookBean> list = new ArrayList<>();
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE is_active=true AND " + filter + "=?" + setOrderString(order);
			
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
						book.setActive(true);
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
			String selectSQL = "SELECT DISTINCT genre FROM " + TABLE_NAME + " WHERE is_active=true ORDER BY genre ASC";
			
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
	                 " SET name=?, author=?, genre=?, price=?, description=?, stock_quantity=?, editor=?, is_active=?, path=?, mime_type=?" +
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
				statement.setBoolean(8, book.isActive());
				statement.setString(9, book.getPath());
				statement.setString(10, book.getMimeType());
				statement.setString(11, book.getCode());
				statement.executeUpdate();
			}
		}
		
		@Override
		public boolean isRegistered(String code) throws SQLException{
			BookBean book = doRetriveByCode(code);
			return book != null;
		}
		
		public ArrayList<BookBean> doRetrieveByString(String query, String order) throws SQLException {
			String selectSQL = "SELECT * FROM " + TABLE_NAME + 
                    " WHERE is_active=true AND (name LIKE ? OR author LIKE ? OR code LIKE ?)" + 
                    setOrderString(order);
			ArrayList<BookBean> books = new ArrayList<BookBean>();
			
			try(Connection connection = ds.getConnection();
					PreparedStatement statement = connection.prepareStatement(selectSQL)) {
				String searchPattern = "%" + query + "%";
				statement.setString(1, searchPattern);
				statement.setString(2, searchPattern);
				statement.setString(3, query);
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
		                book.setActive(true);
		                book.setPath(rs.getString("path"));
		                book.setMimeType(rs.getString("mime_type"));
		                books.add(book);
					}
				}
			}
			
			return books.isEmpty() ? null : books;
		}
		
		public String setOrderString(String order) {
			if(order == null)
				return " ORDER BY name ASC";
			
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
