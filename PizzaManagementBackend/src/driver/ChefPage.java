package driver;

import java.util.Scanner;

import service.DAOImpl;

public class ChefPage {
	
	public void ChefLogin(String username,String password) {
		System.out.println("Type Create to create a new topping");
		System.out.println("\n Type GetToppingId to get a topping id based on name");
		System.out.println("\n Type GetAllToppings to get all toppings avaiable");
		System.out.println("\n Type UpdateTopping to update a topping of a specific name");
		System.out.println("\n Type DeleteTopping to delete a specific Topping");
		System.out.println("\n Type Leave to leave");
		Scanner input = new Scanner(System.in);
		String toppingName;
		int id;
		DAOImpl dimpl = new DAOImpl();
		boolean running = true;
		while(running) {
			switch(input.next()) {
			case "Create":
				System.out.println("Type the name of the topping you want to create");
				toppingName = input.next();
				if(dimpl.createToppings(toppingName) == false) {
					System.out.println("Please create a different topping that already exists");
					break;
				} else {
					System.out.println("Topping created succesfully");
					break;
				}
			case "GetToppingId":
				System.out.println("Type the name of the topping you want an id for");
				toppingName = input.next();
				id = dimpl.getPizzaId(toppingName);
				if(id == -1) {
					System.out.println("That topping does not exist");
					break;
				} else {
					System.out.println(id + " is the id");
					break;
				}
			case "GetAllToppings":
				System.out.println(dimpl.getAllToppings() + " These are the toppings");
				break;
			case "UpdateTopping":
				System.out.println("Type the id you want to change topping name for");
				id = input.nextInt();
				System.out.println("Type the name you want for the topping");
				toppingName = input.next();
				if(dimpl.updateTopping(toppingName, id) == true) {
					System.out.println("Topping succesffully updated");
				}else {
					System.out.println("Error");
				}
				break;
			case "DeleteTopping":
				System.out.println("Type the id of the topping you wish to delete");
				id = input.nextInt();
				System.out.println("Type Yes if you are certain you want to delete this topping else type No");
				String choice = input.next();
				if(choice.equals("Yes")) {
					System.out.println("The result of executing that query was :" + dimpl.deleteTopping(id));
				} else if (choice.equals("No")) {
					System.out.println("Have a nice day");
				}
				break;
			case "Leave":
				running = false;
				break;
			default :
				break;
			}
		}
		input.close();
	}
}
