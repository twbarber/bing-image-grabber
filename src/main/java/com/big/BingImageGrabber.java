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
		int numImages = -1;
		boolean goodNumber = false;
		
		System.out.println("\nBing Image Grabber 0.2.1\n");

		Menu mainMenu = new Menu();
		
		String queryChoice = queryMenu();
		
		if(queryChoice.equalsIgnoreCase("random"))
			numImages = 30;
		else{
			adult = filterMenu();
			System.out.print("\nDesired Number of Images (Max 1000): ");
			numImages = in.nextInt();
			while(!goodNumber) {
				if(numImages < 0 || numImages > 1000) {
					System.out.print("\nPlease enter a valid number of images: ");
					numImages = in.nextInt();
				}
				else goodNumber = true;
			}
		}
		BIGHandler myBigHandler = new BIGHandler(queryChoice, numImages, adult);
	}
}