package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Connect;

public class UserDao {
	Connect connection;
	
	public UserDao() throws ClassNotFoundException, SQLException {
		connection = new Connect();
	}
	
	public User getUser(String e , String p) throws SQLException {
		User user;
		String query = "select name, email from user where email = ? and password = ?";
		PreparedStatement stmt = connection.getConnection().prepareStatement(query);
		stmt.setString(1, e);
		stmt.setString(2, p);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = "";
			user = new User(name, email, password);
		} else {
			user = null;
		}
		return user;
	}
	
	public ArrayList<User> getUsers() throws SQLException {
		ArrayList users = new ArrayList();
		User user;
		String query = "select * from user";
		PreparedStatement stmt = connection.getConnection().prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = rs.getString("password");
			user = new User(name, email, password);
			users.add(user);
		}
		
		return users;
		
	}
	
	
	public boolean addUser(User user) throws SQLException {
		String query = "insert into user (name, email, password) values (?, ?, ?)";
		PreparedStatement stmt = connection.getConnection().prepareStatement(query);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getPassword());
		stmt.execute();
		return true;
	}
}
