package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.JDBCConnection;
import models.Pizza;
import models.PizzaToppings;

public class PizzaDAO {

	private Connection conn = JDBCConnection.getConnection();
	//Implementing CRUD operations
	public boolean createPizza(String pizzaName, ArrayList<PizzaToppings> toppings) {
		
		return false;
	}
	
	public ArrayList<Pizza> getPizza(String pizzaName) {
		String sql = "select * from Pizza_manager.Pizzas where pizzaName = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pizzaName);
			ResultSet rs = ps.executeQuery();
			ArrayList<Pizza> pizzas = new ArrayList<>();
			if(rs.next()) {
				Pizza p = new Pizza();
				p.setId(rs.getInt("pizzaID"));
				p.setPizzaName(pizzaName);
				p.setPizzaToppings(rs.getString("pizzaToppings"));
				pizzas.add(p);
			}
			return pizzas;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//update should allow update an existing pizza ie change name and then update toppings.
}
