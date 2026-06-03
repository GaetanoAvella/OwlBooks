package it.unisa.storage.order.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.BookBean;
import it.unisa.model.DetailOrderBean;
import it.unisa.model.PurchaseOrderBean;

public class PurchaseOrderDaoImpl implements PurchaseOrderDao{
	private static final String PURCHASE_ORDER = "purchase_order";
	private static final String DETAIL_ORDER = "detail_order";
    private DataSource ds;
	
    public PurchaseOrderDaoImpl(DataSource ds) {
    	this.ds = ds;
    }
    
	@Override
	public void doSave(PurchaseOrderBean order) throws SQLException {
		String insertOrder = "INSERT INTO " + PURCHASE_ORDER + " (user_id, order_code, order_date, total, payment_method)"
				+ " VALUES (?,?,?,?,?)";
		String insertDetail = "INSERT INTO " + DETAIL_ORDER + " (order_id, book_id, quantity, price_at_purchase)"
				+ " VALUES (?,?,?,?)"; 
		String updateStock = "UPDATE book SET stock_quantity = stock_quantity - ? WHERE id = ?";
		
		Connection connection = null;
		
		try{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			try(PreparedStatement psOrder = connection.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
				psOrder.setInt(1, order.getUserId());
				psOrder.setString(2, order.getOrderCode());
				psOrder.setDate(3, order.getOrderDate());	
				psOrder.setDouble(4, order.getTotal());
				psOrder.setString(5, order.getPaymentMethod(false));
				psOrder.execute();
			
				int orderId = -1;
				try(ResultSet keys = psOrder.getGeneratedKeys()) {
					if(keys.next())
						orderId = keys.getInt(1);
				}
				
				try(PreparedStatement psDetail = connection.prepareStatement(insertDetail)) {
					for (DetailOrderBean detail : order.getDetails()) {
			            psDetail.setInt(1, orderId);
			            psDetail.setInt(2, detail.getBook().getId());
			            psDetail.setInt(3, detail.getQuantity());
			            psDetail.setDouble(4, detail.getPriceAtPurchase());
			            psDetail.addBatch();
			        }
					 psDetail.executeBatch();
				}
				
				try(PreparedStatement psStock = connection.prepareStatement(updateStock)) {
					for (DetailOrderBean detail : order.getDetails()) {
			            psStock.setInt(1, detail.getQuantity());
			            psStock.setInt(2, detail.getBook().getId());
			            psStock.addBatch();
			        }
			        psStock.executeBatch();
				}
			}
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
	            connection.rollback(); 
	        }
			throw e;
		} finally {
	        if (connection != null) {
	            connection.setAutoCommit(true); 
	            connection.close(); 
	        }
	    }
		
	}

	@Override
	public ArrayList<PurchaseOrderBean> doRetriveByUser(int userId) throws SQLException {
		ArrayList<PurchaseOrderBean> orders = new ArrayList<PurchaseOrderBean>();
		String selectSQL = "SELECT * FROM " + PURCHASE_ORDER + " WHERE user_id=?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)) {
		
			statement.setInt(1, userId);
			try(ResultSet rs = statement.executeQuery()) {
				while(rs.next()) {
					PurchaseOrderBean order = new PurchaseOrderBean(false);
					order.setId(rs.getInt("id"));
					order.setUserId(rs.getInt("user_id"));
					order.setOrderCode(rs.getString("order_code"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setTotal(rs.getDouble("total"));
					order.setPaymentMethod(rs.getString("payment_method"));
					orders.add(order);
				}
			}
		}
		
		return orders;
	}

	@Override
	public PurchaseOrderBean doRetriveByCode(String orderCode) throws SQLException {
		String selectSQL = "SELECT * FROM " + PURCHASE_ORDER + " WHERE order_code=?";
		String selectDetailsSQL = "SELECT d.id AS detail_id, d.order_id, d.book_id, d.quantity, d.price_at_purchase, " +
                "b.code AS book_code, b.name, b.author, b.genre, b.price, b.description, b.stock_quantity, b.editor, b.path, b.mime_type " +
                "FROM " + DETAIL_ORDER + " d " +
                "JOIN book b ON d.book_id = b.id " +
                "WHERE d.order_id = ?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)) {
		
			statement.setString(1, orderCode);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) {
					PurchaseOrderBean order = new PurchaseOrderBean(false);
					order.setId(rs.getInt("id"));
					order.setUserId(rs.getInt("user_id"));
					order.setOrderCode(rs.getString("order_code"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setTotal(rs.getDouble("total"));
					order.setPaymentMethod(rs.getString("payment_method"));
					
					try(PreparedStatement detailStatement = connection.prepareStatement(selectDetailsSQL)) {
						detailStatement.setInt(1, order.getId());
						
						try(ResultSet rsDetail = detailStatement.executeQuery()) {
							while(rsDetail.next()) {
								DetailOrderBean detail = new DetailOrderBean();
								detail.setId(rsDetail.getInt("detail_id"));		
								detail.setOrderId(rsDetail.getInt("order_id"));
	                            detail.setBookId(rsDetail.getInt("book_id"));
	                            detail.setQuantity(rsDetail.getInt("quantity"));
	                            detail.setPriceAtPurchase(rsDetail.getDouble("price_at_purchase"));
	                            
	                            BookBean book = new BookBean();
	                            book.setId(rsDetail.getInt("book_id"));
	                            book.setCode(rsDetail.getString("book_code"));
	                            book.setName(rsDetail.getString("name"));
	                            book.setAuthor(rsDetail.getString("author"));
	                            book.setGenre(rsDetail.getString("genre"));
	                            book.setPrice(rsDetail.getFloat("price"));
	                            book.setDescription(rsDetail.getString("description"));
	                            book.setStock_quantity(rsDetail.getInt("stock_quantity"));
	                            book.setEditor(rsDetail.getString("editor"));
	                            book.setPath(rsDetail.getString("path"));
	                            book.setMimeType(rsDetail.getString("mime_type"));
	                            
	                            detail.setBook(book);
	                            order.getDetails().add(detail);
							}
						}
					}
					
					return order;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public ArrayList<PurchaseOrderBean> doRetrieveAll() throws SQLException {
		ArrayList<PurchaseOrderBean> orders = new ArrayList<>();
		String selectSQL = "SELECT * FROM " + PURCHASE_ORDER + " ORDER BY order_date DESC";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL);
				ResultSet rs = statement.executeQuery()) {
			while(rs.next()) {
				PurchaseOrderBean order = new PurchaseOrderBean(false);
				
	            order.setId(rs.getInt("id"));
	            order.setUserId(rs.getInt("user_id"));
	            order.setOrderCode(rs.getString("order_code"));
	            order.setOrderDate(rs.getDate("order_date"));
	            order.setTotal(rs.getDouble("total"));
	            order.setPaymentMethod(rs.getString("payment_method"));
	            orders.add(order);
			}
		}
		return orders;
	}
	
	@Override
	public ArrayList<PurchaseOrderBean> doRetrieveAllbyTime(Date from, Date to) throws SQLException {
		ArrayList<PurchaseOrderBean> orders = new ArrayList<>();
		String selectSQL = "SELECT * FROM " + PURCHASE_ORDER + " WHERE DATE(order_date) >= DATE(?) AND DATE(order_date) <= DATE(?) ORDER BY order_date DESC";

	    try(Connection connection = ds.getConnection();
	        PreparedStatement statement = connection.prepareStatement(selectSQL)) {
	        statement.setDate(1, from);
	        statement.setDate(2, to);
	        try(ResultSet rs = statement.executeQuery()) {
	            while(rs.next()) {
	                PurchaseOrderBean order = new PurchaseOrderBean(false);
	                order.setId(rs.getInt("id"));
	                order.setUserId(rs.getInt("user_id"));
	                order.setOrderCode(rs.getString("order_code"));
	                order.setOrderDate(rs.getDate("order_date"));
	                order.setTotal(rs.getDouble("total"));
	                order.setPaymentMethod(rs.getString("payment_method"));
	                orders.add(order);
	            }
	        }
	    }
	    return orders;
	}

}
