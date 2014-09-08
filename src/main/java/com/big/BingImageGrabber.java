package com.big;
/* 
	Bing Image Grabber - Version 0.2.1

	Main class. Gets BingID from user and makes objects.

	TODO: 
		- Make iterations to run multiple querys
 */

import java.io.File;
import java.util.Scanner;

public class BingImageGrabber {

	// Sets default values of content filter to Moderate, and images to 30
	// If custom search is chosen, user enters search term, and chooses other parameters
	public static void main(String[] args) throws Exception {

		String adult = "Moderate";
		int numberImages = -1;
		
		
		System.out.println("\nBing Image Grabber 0.2.1\n");

		Menu mainMenu = new Menu();
		mainMenu.firstRun();
		
		String queryChoice = mainMenu.queryMenu();
		
		if(queryChoice.equalsIgnoreCase("random"))
			numberImages = 30;
		else{
			adult = mainMenu.filterMenu();
			numberImages = mainMenu.countMenu();
		}
	}
}