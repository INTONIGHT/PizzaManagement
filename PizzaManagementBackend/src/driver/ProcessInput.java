package driver;


import java.util.Scanner;



public class ProcessInput {
	
	public void processString() {
		Scanner input = new Scanner(System.in);
		
		String username, password;
		
		int  switchCase;
		boolean running = true;
		while(running) {
			System.out.println("Type 1 to Login as an owner 2 as a chef 3 to logout");
			switchCase = input.nextInt();
			switch(switchCase) {
			case 1 :
				System.out.println("Please type in the username");
				username = input.next();
				System.out.println("Type your password");
				password = input.next();
				try {
					OwnerPage op = new OwnerPage();
					op.OwnerLogin(username, password);
					
				} catch(NullPointerException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Please type in the username");
				username = input.next();
				System.out.println("Type your password");
				password = input.next();
				try {
					
				} catch(NullPointerException e) {
					e.printStackTrace();
				}
				break;
			case 3 :
				running = false;
				break;
			}
			
		}
		input.close();
	}
}
