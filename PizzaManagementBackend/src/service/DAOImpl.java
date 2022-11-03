package service;

import java.util.ArrayList;

import daos.PizzaDAO;
import daos.PizzaToppingsDAO;
import daos.UserDAO;
import models.Pizza;
import models.PizzaToppings;
import models.User;

public class DAOImpl {
	PizzaDAO pdao = new PizzaDAO();
	PizzaToppingsDAO ptdao = new PizzaToppingsDAO();
	UserDAO udao = new UserDAO();
	public User Login(String username,String password) {
		User u = udao.Login(username, password);
		return u;
	}
	public boolean createPizza(String pizzaName, ArrayList<PizzaToppings> toppings) {
		boolean b = pdao.createPizza(pizzaName, toppings);
		return b;
	}
	public boolean getToppings(ArrayList<PizzaToppings> toppings) {
		boolean b= pdao.getToppings(toppings);
		return b;
	}
	public Pizza getPizza(String pizzaName) {
		Pizza p = pdao.getPizza(pizzaName);
		return p;
	}
	public ArrayList<Pizza> getAllPizzas(){
		ArrayList<Pizza> pizzas = pdao.getAllPizzas();
		return pizzas;
	}
	public int getPizzaId(String pizzaName) {
		int id = pdao.getId(pizzaName);
		return id;
	}
	public boolean updateToppingsOnPizza(int pizzaId, ArrayList<PizzaToppings> pizzaToppings) {
		boolean b = pdao.updateToppingsOnPizza(pizzaId, pizzaToppings);
		return b;
	}
	public boolean updatePizzaName(int pizzaId,String pizzaName) {
		boolean b = pdao.updatePizzaName(pizzaId, pizzaName);
		return b;
	}
	public boolean updatePizzaNameAndToppings(int pizzaId,String pizzaName, ArrayList<PizzaToppings> pizzaToppings) {
		boolean b = pdao.updatePizzaNameAndToppings(pizzaId, pizzaName, pizzaToppings);
		return b;
	}
	public boolean deletePizza(int pizzaId) {
		boolean b = pdao.deletePizza(pizzaId);
		return b;
	}
	public boolean createToppings(String toppingName) {
		boolean b = ptdao.createTopping(toppingName);
		return b;
	}
	public boolean checkTopping(String toppingName) {
		boolean b = ptdao.checkTopping(toppingName);
		return b;
	}
	public int getToppingId(String toppingName) {
		int id = ptdao.getToppingId(toppingName);
		return id;
	}
	public ArrayList<PizzaToppings> getAllToppings(){
		ArrayList<PizzaToppings> pizzaToppings = ptdao.getAllToppings();
		return pizzaToppings;
	}
	public boolean updateTopping(String toppingName, int toppingsId) {
		boolean b = ptdao.updateTopping(toppingName, toppingsId);
		return b;
	}
	public boolean deleteTopping(int toppingsId) {
		boolean b = ptdao.deleteTopping(toppingsId);
		return b;
	}
}
