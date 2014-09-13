package com.big;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/* 
	Bing Image Grabber - Version 0.2.2
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
		this.queryTerm = mainMenu.queryMenu();
		
		if (queryTerm.equalsIgnoreCase("random")) {
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
				System.err.println("There was a problem with authentication.");
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
				String jsonAsString = urlGrabber.runQuery(aBingURL);
				imageURLList.addAll(urlGrabber.parseURLs(jsonAsString));
			} catch (Exception e) {
				System.err.println("There was a problem getting the image URLs.");
			}
		}
		return imageURLList; 
	}
	
	private void downloadImages(Collection<URL> imageURLs) { 
		ImageDownloader imageDownloader = new ImageDownloader(imageURLs);
		try {
			imageDownloader.downloadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String generateQueryTerm() {
		int generatedNum = 100000 + (int)(Math.random() * ((899999) + 1));
		return String.valueOf(generatedNum);
	}
	
}