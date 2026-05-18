package it.unisa.storage.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

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
		
		
		try(Connection connection = ds.getConnection()) {
			try(PreparedStatement psOrder = connection.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
				psOrder.setInt(1, order.getUserId());
				psOrder.setString(2, order.getOrderCode());
				psOrder.setDate(3, order.getOrderDate());	
				psOrder.setDouble(4, order.getTotal());
				psOrder.setString(5, order.getPaymentMethod());
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
		PurchaseOrderBean order = new PurchaseOrderBean(false);
		String selectSQL = "SELECT * FROM " + PURCHASE_ORDER + " WHERE order_code=?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)) {
		
			statement.setString(1, orderCode);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) {
					order.setId(rs.getInt("id"));
					order.setId(rs.getInt("user_id"));
					order.setOrderCode("order_code");
					order.setOrderDate(rs.getDate("order_date"));
					order.setTotal(rs.getDouble("total"));
					order.setPaymentMethod(rs.getString("payment_method"));
				}
			}
		}
		
		return order;
	}

}
