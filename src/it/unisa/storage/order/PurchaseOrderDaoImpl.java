package it.unisa.storage.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.PurchaseOrderBean;

public class PurchaseOrderDaoImpl implements PurchaseOrderDao{
	private static final String TABLE_NAME = "purchase_order";
    private DataSource ds;
	
    public PurchaseOrderDaoImpl(DataSource ds) {
    	this.ds = ds;
    }
	
	@Override
	public void doSave(PurchaseOrderBean order) throws SQLException {
		String insertSQL = "INSERT INTO " + TABLE_NAME + " (user_id, order_code, order_date, total, payment_method)"
				+ " VALUES (?,?,?,?,?)";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertSQL)) {
			statement.setInt(1, order.getUserId());
			statement.setString(2, order.getOrderCode());
			statement.setDate(3, order.getOrderDate());
			statement.setDouble(4, order.getTotal());
			statement.setString(5, order.getPaymentMethod());
			statement.execute();
		}
	}

	@Override
	public ArrayList<PurchaseOrderBean> doRetriveByUser(int userId) throws SQLException {
		ArrayList<PurchaseOrderBean> orders = new ArrayList<PurchaseOrderBean>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE user_id=?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)) {
		
			statement.setInt(1, userId);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) {
					PurchaseOrderBean order = new PurchaseOrderBean(false);
					order.setId(rs.getInt("id"));
					order.setId(rs.getInt("user_id"));
					order.setOrderCode("order_code");
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
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE order_code=?";
		
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
