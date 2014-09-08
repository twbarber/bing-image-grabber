package com.big;

import java.io.File;
import java.util.Scanner;

public class Menu {
	
	private Scanner systemInput = new Scanner(System.in);
	
	public void firstRun() {

		String userHome = System.getProperty("user.home");
		String strDirectory = userHome + "/big";
		String imageDirectory = strDirectory + "/images";

		File bigHomeDirectoryFile = new File(strDirectory);
		File imageDirectoryFile = new File(imageDirectory);

		if (!bigHomeDirectoryFile.exists()) {
			boolean success = bigHomeDirectoryFile.mkdir();
			if(success)	{
				boolean success2 = imageDirectoryFile.mkdir();
				if(success2)	
					System.out.println("BIG Directory Initialized\n");
			}	
		}
	}

	public String queryMenu() {

		boolean validChoice = false;

		System.out.println("Choose Search Term: \n" +
				"\n1) Random \n2) Custom");  

		while(!validChoice) {
			System.out.print("\nChoice: ");

			String choice = this.systemInput.nextLine();

			if(choice.equals("1") || choice.equalsIgnoreCase("random"))
				return "random";
			else if(choice.equals("2") || choice.equalsIgnoreCase("custom")) {
				System.out.print("\nEnter Search Term: ");
				return this.systemInput.nextLine().replaceAll(" ", "_");
			}	
			else 
				System.out.println("\nInvalid Option");
		}
		return "random";	// Returns random as default if somehow it gets here
	}

	// Displays menu of available content filters.
	// User chooses by entering number, loops until good input
	public String filterMenu() {
		
		boolean validChoice = false;

		System.out.println("\nChoose Adult Content Settings: \n" +
				"\n1) Strict \n2) Moderate \n3) Off");

		while(!validChoice) {
			System.out.print("\nChoice: ");

			String choice = this.systemInput.nextLine();

			if(choice.equals("1") || choice.equalsIgnoreCase("strict"))
				return "Strict";
			else if(choice.equals("2") || choice.equalsIgnoreCase("moderate")) {
				return "Moderate";
			}	
			else if(choice.equals("3") || choice.equalsIgnoreCase("off")) {
				return "Off";
			}	
			else 
				System.out.println("\nInvalid Option");
		}
		return "Moderate";	// Returns Moderate as default if somehow it gets here

	}
	
	public int countMenu() {
		System.out.print("\nDesired Number of Images (Max 1000): ");
		boolean goodNumber = false;
		int numberImages = this.systemInput.nextInt();
		while(!goodNumber) {
			if(numberImages < 0 || numberImages > 1000) {
				System.out.print("\nPlease enter a valid number of images: ");
				numberImages = this.systemInput.nextInt();
			}
			else goodNumber = true;
		}
		return numberImages;
	}
}