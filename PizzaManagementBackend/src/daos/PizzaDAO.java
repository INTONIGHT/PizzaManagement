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
		if(getToppings(toppings) == true) {
			return false;
		}
		/**
		 * insert into Pizzas(pizzaName,pizzaToppings)
			values('Supreme','Pepporoni,Sausage,Ham,Cheese,Onions,Cheese,Peppers');
			modify column pizzaID int auto_increment
;
		 */
		String sql = "insert into Pizza_manager.Pizzas(pizzaName,pizzaToppings) values(?,?)";
		return false;
	}
	
	public boolean getToppings(ArrayList<PizzaToppings> toppings) {
		String sql = "select * from Pizza_manager.Pizzas where pizzaToppings = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			PizzaToppings pt = new PizzaToppings();
			for(int i =0; i<toppings.size();i++) {
				pt.setId(toppings.get(i).getId());
				pt.setReferenceId(toppings.get(i).getReferenceId());
				pt.setToppingName(toppings.get(i).getToppingName());
			}
			String top = pt.toString();
			System.out.println(top + "This is what the toppings looks like now");
			ps.setString(1, top);
			ResultSet rs = ps.executeQuery();
			if(rs.next() == false) {
				return false;
			} else {
				do {
					return true;
				} while(rs.next());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Pizza getPizza(String pizzaName) {
		String sql = "select * from Pizza_manager.Pizzas where pizzaName = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pizzaName);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Pizza p = new Pizza();
				p.setId(rs.getInt("pizzaID"));
				p.setPizzaName(pizzaName);
				p.setPizzaToppings(rs.getString("pizzaToppings"));
				return p;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Pizza> getAllPizzas(){
		String sql = "select * from Pizza_manager.Pizzas";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<Pizza> pizzas = new ArrayList<>();
			if(rs.next()) {
				Pizza p = new Pizza();
				p.setId(rs.getInt("pizzaID"));
				p.setPizzaName(rs.getString("pizzaName"));
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
