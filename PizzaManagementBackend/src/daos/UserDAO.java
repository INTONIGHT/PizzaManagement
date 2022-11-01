package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.JDBCConnection;
import models.User;

public class UserDAO {

	
	private Connection conn = JDBCConnection.getConnection();
	//Ideally use Java spring here I will start with prepared statements as I am more familiar with those
	public User Login(String username, String password) {
		String sql = "select * from Pizza_manager.Users where username = ? and password = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("userid"));
				u.setRole(rs.getString("userRole"));
				u.setUsername(username);
				u.setPassword(password);
				return u;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
