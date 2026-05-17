package it.unisa.storage.order;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.PurchaseOrderBean;

public interface PurchaseOrderDao {
	void doSave(PurchaseOrderBean order) throws SQLException;
	ArrayList<PurchaseOrderBean> doRetriveByUser(int userId) throws SQLException;
	PurchaseOrderBean doRetriveByCode(String orderCode) throws SQLException;
}
