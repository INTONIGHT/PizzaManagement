package app;

import java.util.ArrayList;

import daos.PizzaDAO;
import models.Pizza;
import models.PizzaToppings;

public class Driver {
	
	//remove this later on use this for testing purposes
	public static void main(String[] args) {
		PizzaDAO pdao = new PizzaDAO();
		ArrayList<PizzaToppings> pizzaToppings = new ArrayList<>();
		PizzaToppings pep = new PizzaToppings(1,"Pepporoni");
		PizzaToppings chez = new PizzaToppings(2,"Cheese");
		pizzaToppings.add(pep);
		pizzaToppings.add(chez);
		System.out.println(pdao.createPizza("Pepporoni", pizzaToppings));
		
	}
}
