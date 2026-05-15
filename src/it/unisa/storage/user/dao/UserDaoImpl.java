package it.unisa.storage.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import it.unisa.model.BookBean;
import it.unisa.model.UserBean;

public class UserDaoImpl implements UserDao {
	private static final String TABLE_NAME = "user";
	private DataSource ds;
	
	public UserDaoImpl(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public void doSave(UserBean user) throws SQLException {
		String insertSQL = "INSERT INTO " + TABLE_NAME + " (name,surname,address,email,password) VALUES (?,?,?,?,?);";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(insertSQL)) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getAddress());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean doDelete(String email) throws SQLException {
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE email = ?";
        try (Connection connection = ds.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, email);
            int result = preparedStatement.executeUpdate();
            return result != 0;
        }
	}

	@Override
	public UserBean doRetriveByKey(String email) throws SQLException {
		UserBean user = new UserBean();
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email=?";
		
		try(Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(selectSQL)) {
		
			statement.setString(1, email);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) {
					user.setName(rs.getString("name"));
					user.setName(rs.getString("surname"));
					user.setName(rs.getString("address"));
					user.setName(rs.getString("email"));
					user.setName(rs.getString("password"));
				}
			}
		}
		
		return user;	
	}

}
