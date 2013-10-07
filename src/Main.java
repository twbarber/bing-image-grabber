/* 
	Bing Image Grabber - Version 0.1.1

	Main class. Gets BingID from user and makes objects.
	
	TODO: 
		- Make downloads / seed variable
		- Make iterations to run multiple querys
		- Make search options available to be entered manually
*/

import java.util.*;

public class Main {

	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
	
			System.out.println("\nBing Image Grabber 0.1.0\n");
			System.out.print("Enter Bing AppID: ");
			String userAccountKey = in.next();
			String queryChoice = queryMenu();
			String adult = filterMenu();
			System.out.print("\nDesired Number of Images (Max 50): ");
			int numImages = in.nextInt();
			urlGrabber mySerialGrabber = new urlGrabber(userAccountKey, queryChoice, numImages, adult);
			mySerialGrabber.run();	
	}
	
	public static String queryMenu() {
		
		System.out.println("\nChoose Search Term: \n" +
				"\n1) Random \n2) Custom");  
		System.out.print("\nChoice: ");

		int choice = in.nextInt();

		switch(choice) {
			case 1: return "random";
			case 2: System.out.print("\nEnter Search Term: ");
					return in.next();
			default: System.out.println("\nInvalid Option\n");
					 queryMenu();
		}
		// Returns random as default if somehow it gets here
		return "random";
	}
	
	// Displays menu of available content filters.
	// User chooses by entering number, loops until good input
	public static String filterMenu() {
		
		System.out.println("\nChoose Adult Content Settings: \n" +
							"\n1) Strict \n2) Moderate \n3) Off");  
		System.out.print("\nChoice: ");
		
		int choice = in.nextInt();
		
			switch(choice) {
				case 1: return "Strict";
				case 2: return "Moderate";
				case 3: return "Off";
				default: System.out.println("\nInvalid Option\n");
						 filterMenu();
			}
		return "Moderate";				// Returns Moderate as default if somehow it gets here
	}
}