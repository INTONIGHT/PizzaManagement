package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.JDBCConnection;
import models.PizzaToppings;

public class PizzaToppingsDAO {
	private Connection conn = JDBCConnection.getConnection();
	public boolean createTopping(String toppingName) {
		if(checkTopping(toppingName) == true) {
			return false;
		}
		String sql = "insert into Pizza_manager.PizzaToppings(toppingsName) values(?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, toppingName);
			boolean b = ps.execute();
			return b;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkTopping(String toppingName) {
		String sql = "select * from Pizza_manager.PizzaToppings where toppingsName = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, toppingName);	
			ResultSet rs = ps.executeQuery();
			if(rs.next() == false) {
				return false;
			} else {
				do {
					return true;
				} while(rs.next());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public int getToppingId(String toppingName) {
		String sql = "select * from Pizza_manager.PizzaToppings where toppingsName = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, toppingName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int pizzaToppingId = rs.getInt("toppingsId");
				return pizzaToppingId;
			}
		}catch(SQLException e) {
				e.printStackTrace();
			}
			return -1;
		}
	public ArrayList<PizzaToppings> getAllToppings(){
		String sql = "select * from Pizza_manager.PizzaToppings";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<PizzaToppings> pizzaToppings = new ArrayList<>();
			if(rs.next()) {
				PizzaToppings pt = new PizzaToppings();
				pt.setId(rs.getInt("toppingsId"));
				pt.setToppingName(rs.getString("toppingsName"));
				pizzaToppings.add(pt);
			}
			return pizzaToppings;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean updateTopping(String toppingName, int toppingsId) {
		if(checkTopping(toppingName) == true) {
			return false;
		}
		String sql = "update Pizza_manager.PizzaToppings set toppingsName = ? where toppingsId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, toppingName);
			ps.setInt(2, toppingsId);
			boolean b = ps.execute();
			return b;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean deleteTopping(int toppingsId) {
		String sql = "delete from Pizza_Manager.PizzaToppings where toppingsId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, toppingsId);
			boolean b = ps.execute();
			return b;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
