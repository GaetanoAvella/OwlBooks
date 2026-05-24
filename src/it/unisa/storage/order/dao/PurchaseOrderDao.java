package it.unisa.storage.order.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.PurchaseOrderBean;

public interface PurchaseOrderDao {
	public void doSave(PurchaseOrderBean order) throws SQLException;
	public ArrayList<PurchaseOrderBean> doRetriveByUser(int userId) throws SQLException;
	public PurchaseOrderBean doRetriveByCode(String orderCode) throws SQLException;
	public ArrayList<PurchaseOrderBean> doRetrieveAll() throws SQLException;
	public ArrayList<PurchaseOrderBean> doRetrieveAllbyTime(Date from, Date to) throws SQLException;
}
