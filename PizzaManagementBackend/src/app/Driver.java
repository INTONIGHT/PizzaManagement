package app;

import java.util.ArrayList;

import daos.PizzaDAO;
import models.PizzaToppings;

public class Driver {
	
	//remove this later on use this for testing purposes
	public static void main(String[] args) {
		PizzaDAO pdao = new PizzaDAO();
		ArrayList<PizzaToppings> toppings = new ArrayList<>();
		PizzaToppings topping = new PizzaToppings(1,"cheese","cheese");
		toppings.add(topping);
		//topping.setToppingName("Test");
		//toppings.add(topping);
		System.out.println(pdao.getToppings(toppings));
	}
}
