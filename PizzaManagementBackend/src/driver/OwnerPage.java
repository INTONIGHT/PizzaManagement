package driver;

import java.util.ArrayList;
import java.util.Scanner;

import models.PizzaToppings;
import models.User;
import service.DAOImpl;

public class OwnerPage {

	public void OwnerLogin(String username,String password) {
		System.out.println("Type Create to create a new pizza");
		System.out.println("\n Type Update to Update items of a pizza such as name and/or toppings");
		System.out.println("\n Type Delete to delete a pizza");
		System.out.println("\n Type GetAll to get all the pizzas");
		System.out.println("\n Type GetById to get a pizza by id");
		System.out.println("\n Type Leave to go back");
		Scanner input = new Scanner(System.in);
		String pizzaName;
		ArrayList<PizzaToppings> toppings = new ArrayList<>();
		DAOImpl dimpl = new DAOImpl();
		boolean running = true;
		boolean isRunning = true;
		User u = new User();
		u = dimpl.Login(username, password);
		while(running) {
			switch(input.next()) {
			case "Create":
				System.out.println("Type the name of the new pizza");
				pizzaName = input.next();
				while(isRunning) {
					System.out.println("Type the name of the topping you want to add to the pizza type Quit to stop adding toppings");
					String name = input.next();
					if(name.equalsIgnoreCase("Quit")) {
						break;
					}
					PizzaToppings t = new PizzaToppings(name);
					toppings.add(t);
				}
				if(dimpl.createPizza(pizzaName, toppings) == false) {
					System.out.println("Please create a pizza with different toppings");
					break;
				}
					else {
						System.out.println("your pizza was created succesfully");
						break;
					}
			case "Update":
				System.out.println("Type UpdateName to update a name UpdateToppings for toppings and UpdateBoth to do both at the same time");
				String updateChoice = input.next();
				switch(updateChoice) {
				case "UpdateName":
					System.out.println("Type the id of the pizza you want to update");
					int pizzaId = input.nextInt();
					System.out.println("Type the new name you want");
					String newName = input.next();
					if(dimpl.updatePizzaName(pizzaId, newName) == true) {
						System.out.println("Name was updated succesfully");
					}else {
						System.out.println("Something went wrong apologies");
					}
					break;
				case "UpdateToppings":
					System.out.println("Type the id of the pizza you want to update toppings for");
					int idToChange = input.nextInt();
					while(isRunning) {
						System.out.println("Type the name of the topping you want to add to the pizza type Quit to stop adding toppings");
						String name = input.next();
						if(name.equalsIgnoreCase("Quit")) {
							break;
						}
						PizzaToppings tops = new PizzaToppings(name);
						toppings.add(tops);
					}
					if(dimpl.updateToppingsOnPizza(idToChange, toppings) == true) {
						System.out.println("Toppings succesfully updated on that pizza");
					} else {
						System.out.println("Error sorry");
					}
					break;
				case "UpdateBoth":
					System.out.println("Type the id of the pizza you want to update toppings and name for");
					int idOfChange = input.nextInt();
					System.out.println("Type the name you want to change it to");
					String nameNew = input.next();
					while(isRunning) {
						System.out.println("Type the name of the topping you want to add to the pizza type Quit to stop adding toppings");
						String name = input.next();
						if(name.equalsIgnoreCase("Quit")) {
							break;
						}
						PizzaToppings topsChange = new PizzaToppings(name);
						toppings.add(topsChange);
					}
					if(dimpl.updatePizzaNameAndToppings(idOfChange, nameNew, toppings) == true) {
						System.out.println("Succesfully updated everything");
					}else {
						System.out.println("Problem occured");
					}
					break;
				}
				break;
			case "Delete":
				System.out.println("Type the id of the pizza which you wish to delete");
				int pizzaToDelete = input.nextInt();
				System.out.println("Are you certain you wish to delete this pizza? Type Yes if you are No if not");
				String choice = input.next();
				if(choice == "Yes") {
					if(dimpl.deletePizza(pizzaToDelete) == true) {
						System.out.println("Pizza deleted");
					}else {
						System.out.println("Error");
					}
				} else if(choice == "No") {
					break;
				} else {
					break;
				}
			case "GetById":
				System.out.println("This function will get the id of a given pizza name please type the name");
				String nameToFind = input.next();
				System.out.println(dimpl.getPizzaId(nameToFind) + " this is the id for that pizza if it is -1 then the pizza doesnt exist");
				break;
			case "GetAll":
				System.out.println(dimpl.getAllPizzas() + " This is the list of pzizas found");
				break;
			case "Leave":
				running = false;
				break;
			default :
				System.out.println("Nothing interesting happens");
				break;
				}
				
			}
		input.close();
		}
	}

