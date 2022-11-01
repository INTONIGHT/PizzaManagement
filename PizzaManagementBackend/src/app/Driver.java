package app;

import daos.UserDAO;
import models.User;

public class Driver {
	
	//remove this later on use this for testing purposes
	public static void main(String[] args) {
		UserDAO udao = new UserDAO();
		String username = "owner";
		String password = "password";
		User u =udao.Login(username, password);
		System.out.println(u.getRole() + "role returned");
	}
}
