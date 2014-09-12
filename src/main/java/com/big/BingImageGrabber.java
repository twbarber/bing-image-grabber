package com.big;

import java.io.IOException;
import java.net.URL;
/* 
	Bing Image Grabber - Version 0.2.1
 */
import java.util.ArrayList;
import java.util.Collection;

public class BingImageGrabber {

	private final String BIG_VERSION_NUMBER = "0.2.1";
	private String queryFilter;
	private int numberImages;
	private String queryTerm;
	
	public BingImageGrabber() {
		this.queryFilter = "Moderate";
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
		this.queryTerm = mainMenu.queryMenu();
		if (this.queryTerm.equalsIgnoreCase("random")) {
			this.numberImages = 30;
			this.queryTerm = generateQueryTerm();
		} else {
			this.queryFilter = mainMenu.filterMenu();
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
	
	private Collection<URL> buildQuery() {
		QueryBuilder queryBuilder = new QueryBuilder(this.queryTerm, this.numberImages, this.queryFilter);
		
		return null;
	}
	
	private Collection<URL> getImageURLList(String query) {
		
		return new ArrayList<URL>(); 
	}
	
	private void downloadImages() { 
		
		
	}
	
	public String generateQueryTerm() {
		int generatedNum = 100000 + (int)(Math.random() * ((899999) + 1));
		return String.valueOf(generatedNum);
	}
	
}