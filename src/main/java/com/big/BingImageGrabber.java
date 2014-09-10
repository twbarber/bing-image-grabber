package com.big;
/* 
	Bing Image Grabber - Version 0.2.1
 */

public class BingImageGrabber {

	private String adult;
	private int numberImages;
	
	public BingImageGrabber() {
		this.adult = "Moderate";
		this.numberImages = -1;
	}
	
	public void runBingImageGrabber() {
		System.out.println("\nBing Image Grabber 0.2.1\n");

		Menu mainMenu = new Menu();
		mainMenu.firstRun();
		String queryChoice = mainMenu.queryMenu();
		
		if(queryChoice.equalsIgnoreCase("random"))
			this.numberImages = 30;
		else{
			adult = mainMenu.filterMenu();
			numberImages = mainMenu.countMenu();
		}
	}
}