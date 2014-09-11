package com.big;

import java.io.IOException;
/* 
	Bing Image Grabber - Version 0.2.1
 */
import java.util.ArrayList;

public class BingImageGrabber {

	private String adult;
	private int numberImages;
	private final String BIG_VERSION_NUMBER = "0.2.1";
	
	public BingImageGrabber() {
		this.adult = "Moderate";
		this.numberImages = -1;
	}
	
	public void runBingImageGrabber() {
		System.out.println("\nBing Image Grabber " + BIG_VERSION_NUMBER + "\n");
		displayMenus();
		authenticate();
	}
	
	private void displayMenus() {
		Menu mainMenu = new Menu();
		mainMenu.firstRun();
		String queryChoice = mainMenu.queryMenu();
		if(queryChoice.equalsIgnoreCase("random"))
			this.numberImages = 30;
		else{
			this.adult = mainMenu.filterMenu();
			this.numberImages = mainMenu.countMenu();
		}
	}
	
	private void authenticate() {
		KeyHandler keyHandler = new KeyHandler();
		while (!keyHandler.isValidKey()) {
			keyHandler.promptForKey();
			try {
				keyHandler.writeKey();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	private String buildQuery() {
		
		return null;
		
	}
	
	private ArrayList<String> getImageURLList(String query) {
		
		return new ArrayList<String>(); 
	}
	
	private void downloadImages() { 
		
		
	}
	
}