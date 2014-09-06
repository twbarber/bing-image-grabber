package com.big;
/* 
	Bing Image Grabber - Version 0.2.0

	Main class. Gets BingID from user and makes objects.
	
	TODO: 
		- Make iterations to run multiple querys
*/

import java.io.File;
import java.util.Scanner;

public class Main {

	public static Scanner in = new Scanner(System.in);

	// Sets default values of content filter to Moderate, and images to 30
	// If custom search is chosen, user enters search term, and chooses other parameters
	public static void main(String[] args) throws Exception {
			
			String adult = "Moderate";
			int numImages = 30;
			
			System.out.println("\nBing Image Grabber 0.1.1\n");
			firstRun();
			
			String queryChoice = queryMenu();
			if(!queryChoice.equals("random")) {
				adult = filterMenu();
				System.out.print("\nDesired Number of Images (Max 50): ");
				numImages = in.nextInt();
			}
			urlGrabber mySerialGrabber = new urlGrabber(queryChoice, numImages, adult);
			mySerialGrabber.run();	
	}
	
	public static void firstRun() {
		
		String user = System.getProperty("user.name");
		String strDirectory = "/home/" + user + "/big";
		File bigDir = new File(strDirectory);

		if (!bigDir.exists()) {
			boolean success = bigDir.mkdir();
				if(success)	
					System.out.println("Directory Initialized\n");
		}
	}
	
	public static String queryMenu() {

		System.out.println("Choose Search Term: \n" +
				"\n1) Random \n2) Custom");  
		System.out.print("\nChoice: ");

		String choice = in.nextLine();
		
		if(choice.equals("1") || choice.equalsIgnoreCase("random"))
			return "random";
		else if(choice.equals("2") || choice.equalsIgnoreCase("custom")) {
			System.out.print("\nEnter Search Term: ");
			return in.next();
		}	
		else {
			System.out.println("\nInvalid Option");
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
		
		String choice = in.nextLine();
		
		if(choice.equals("1") || choice.equalsIgnoreCase("strict"))
			return "Strict";
		else if(choice.equals("2") || choice.equalsIgnoreCase("moderate")) {
			return "Moderate";
		}	
		else if(choice.equals("3") || choice.equalsIgnoreCase("off")) {
			return "Off";
		}	
		else {
			System.out.println("\nInvalid Option");
			filterMenu();
		}
		return "Moderate";	// Returns Moderate as default if somehow it gets here
	}
}