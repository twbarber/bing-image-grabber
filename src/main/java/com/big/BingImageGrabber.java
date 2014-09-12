package com.big;

import java.io.IOException;
import java.net.URL;
/* 
	Bing Image Grabber - Version 0.2.1
 */
import java.util.ArrayList;
import java.util.Collection;

public class BingImageGrabber {

	private final String BIG_VERSION_NUMBER = "0.2.2";
	private String encryptedKey;
	
	public BingImageGrabber() {

	}
	
	public void runBingImageGrabber() {
		System.out.println("\nBing Image Grabber " + BIG_VERSION_NUMBER + "\n");
		displayMenus();
		authenticate();
	}
	
	private void displayMenus() {
		int numberImages;
		String queryFilter;
		
		Menu mainMenu = new Menu();
		mainMenu.firstRun();
		String queryTerm = mainMenu.queryMenu();
		
		if (queryTerm.equalsIgnoreCase("random")) {
			numberImages = 30;
			queryTerm = generateQueryTerm();
			
		} else {
			queryFilter = mainMenu.filterMenu();
			numberImages = mainMenu.countMenu();
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
	
	private Collection<URL> buildQuery(String queryTerm, int numberImages, String queryFilter) {
		QueryBuilder queryBuilder = new QueryBuilder(queryTerm, numberImages, queryFilter);
		
		return null;
	}
	
	private Collection<URL> getImageURLList(Collection<URL> bingURLs) {
		ArrayList<URL> imageURLList = new ArrayList<URL>();
		URLGrabber urlGrabber = new URLGrabber(this.encryptedKey);
		for (URL aBingURL : bingURLs) {
			try {
				String jsonString = urlGrabber.runQuery(aBingURL);
				imageURLList.addAll(urlGrabber.parseURLs(jsonString));
			} catch (Exception e) {
				
			}
		}
		return imageURLList; 
	}
	
	private void downloadImages() { 
		
		
	}
	
	public String generateQueryTerm() {
		int generatedNum = 100000 + (int)(Math.random() * ((899999) + 1));
		return String.valueOf(generatedNum);
	}
	
}