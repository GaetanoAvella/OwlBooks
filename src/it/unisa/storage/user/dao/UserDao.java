package it.unisa.storage.user.dao;

import java.sql.SQLException;

import it.unisa.model.UserBean;

public interface UserDao {
	public void doSave(UserBean user) throws SQLException;
	public boolean doDelete(String email) throws SQLException;
	public UserBean doRetriveByKey(String email) throws SQLException;
	public boolean isRegistered(String email) throws SQLException;
	public boolean checkPassword(String email, String password) throws SQLException;
	public void doUpdate(UserBean user) throws SQLException;
}
