package com.big;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/* 
	Bing Image Grabber - Version 0.2.1
 */
import java.util.ArrayList;
import java.util.Collection;

public class BingImageGrabber {

	private final String BIG_VERSION_NUMBER = "0.2.2";
	private String encryptedKey;
	int numberImages = -1;
	String queryFilter = "Moderate";
	String queryTerm;
	
	public BingImageGrabber() {
		
	}
	
	public void runBingImageGrabber() {
		System.out.println("\nBing Image Grabber " + BIG_VERSION_NUMBER + "\n");
		displayMenus();
		authenticate();
		try {
			Collection<URL> bingURLs = buildQuery(this.queryTerm, this.numberImages, this.queryFilter);
			Collection<URL> imageURLs = getImageURLList(bingURLs);
			downloadImages(imageURLs);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void displayMenus() {
		
		Menu mainMenu = new Menu();
		mainMenu.firstRun();
		queryTerm = mainMenu.queryMenu();
		
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
	
	private Collection<URL> buildQuery(String queryTerm, int numberImages, String queryFilter) throws MalformedURLException {
		QueryBuilder queryBuilder = new QueryBuilder(queryTerm, numberImages, queryFilter);
		queryBuilder.encodeParameters();
		return queryBuilder.generateQuerys();
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
	
	private void downloadImages(Collection<URL> imageURLs) { 
		ImageDownloader imageDownloader = new ImageDownloader(imageURLs);
		
	}
	
	public String generateQueryTerm() {
		int generatedNum = 100000 + (int)(Math.random() * ((899999) + 1));
		return String.valueOf(generatedNum);
	}
	
}