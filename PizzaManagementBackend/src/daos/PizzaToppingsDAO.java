package daos;

import java.sql.Connection;

import connection.JDBCConnection;

public class PizzaToppingsDAO {
	private Connection conn = JDBCConnection.getConnection();
	public boolean createTopping(String toppingName) {
		
		return false;
	}
	public int getToppingId(String toppingName) {
		
		return -1;
	}
	public boolean updateTopping(String ToppingName) {
		
		return false;
	}
	public boolean deleteTopping(String ToppingName) {
		
		return false;
	}
}
