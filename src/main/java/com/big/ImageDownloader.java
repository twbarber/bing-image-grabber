package com.big;
/*
 	imageDownloader class that handles the actual retrieval of images.
 	
 	The imageDownloader is passed in a serialGrabber object, and it uses
 	the fields to populate:
 	
 		- The array of parsed URLs
 		- The raw query term for directory organization
 		
 	The user enters where they want to save the images, and then the images 
 	are downloaded from the array of URLs. 
 	
 	The first time this program saves images to a directory, it will create a 
 	folder with a name dictated by the user. Each time a new query stores it's 
 	result in the same folder, sub-folders are created starting with the first query, 
 	named after the query itself:
 	
 		Example: Query - 456123 
 				 A folder inside the the users created directory named "456123" 
 				 will be created, and the images resulting form that search will
 				 be saved there.
 	
 	Images are saved with the names:
 	
 		"img"  + arrayIndex
 		Example: img2 for array imageURLs[2];
 	
 	After a directory is created, URL connection is opened for URL in array.
 	Image is read in 2Kb at a time, an file is built.
 	
 	Download time varied depending on the size of the image.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;

public class ImageDownloader {
	
	private ArrayList<String> imageURLs = new ArrayList<String>();		// Array of parsed URLs
	private String rawQueryTerm;		// Query for directory organization
	private String queryDir;			// Storage Directory
	
	// Constructor copies the imageURL array into this object.
	// It also copies in the raw query for directory structure
	public ImageDownloader(URLGrabber myUrlGrabber) {
		for(String imageURL : myUrlGrabber.parsedURLs)
			this.imageURLs.add(imageURL);
	}
	
	public void run() throws IOException {
		
		this.queryDir = makeDirectories();
		download();
	}
	
	// Code is pretty self explanatory.
	// Asks the user for a directory to write the image files to.
	// If it exists, go inside and create sub-folder
	// If it doesn't exist, make it, then make sub-folder inside of it
	// Print to console any time a directory is made
	// Returns directory to be used with download() method
	public String makeDirectories() {

		String userHome = System.getProperty("user.home");

		File imageDir = new File(userHome + "/big/images");
		File queryDir = new File(imageDir + "/" + this.rawQueryTerm);
		
		if (!imageDir.exists()) {
			boolean success = imageDir.mkdir();
			if (success)
				System.out.println("\nBing Directory: " + imageDir.toString() + " created");
		}
		
		if (!queryDir.exists()) {
			boolean success = queryDir.mkdir();
			if (success)
				System.out.println("\nQuery Directory: " + queryDir.toString() + " created");
		}
		return queryDir.toString();
	}
	
	// Saves images from array of URLs locally. 
	// 
	public void download() throws IOException {

		int imageCount = 0; 											
		
		// Lets user know downloading started, starts timer
		System.out.print("\nDownloading Images");	
		long startTime = System.nanoTime();
		
		// Loops over all URLs in array
		for(int i = 0; i < imageURLs.size(); i++) {						
			
			// Lets user know there's progress
			System.out.print(".");					
			// Try - Catch to make sure URL is good
			try {								
				// Builds string for img file name
				String fileName = queryDir + "/img" + i; 				
				File img = new File(fileName);
				URL url = new URL(imageURLs.get(i));			// Creates URL object from URL String
				InputStream is = url.openStream();			// Opens input stream on image at end of URL

				OutputStream os = new FileOutputStream(img.toString());	// Opens writer to img file

				byte[] b = new byte[2048];
				int length;

				// Loops to the end of the file
				while ((length = is.read(b)) != -1) {
					// Writes length bytes from the specified byte array b
					// Starting at offset 0 to this output stream.
					os.write(b, 0, length);
				}

				is.close();				// Close inputStream
				os.close();				// Close outputStream

				imageCount++;			// Increments for final download count.
			} catch (IOException e) {
			}
			
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double seconds = duration / 1000000000.0; // Converts time to download all images to seconds
		
		makeLog(seconds);
		
		System.out.printf("\n\nSaved %d images in %.2f seconds.", imageCount, seconds);
	}
	
	
	
	// Writes a log file to the download directory.
	// Contains all URLs and time to download full directory
	public void makeLog(Double seconds) throws IOException {
		
		File logFile = new File(queryDir + "/" + rawQueryTerm + ".txt");
		Writer output = new BufferedWriter(new FileWriter(logFile));
		try {
			for(String i : this.imageURLs)
				output.write(i + "\n");
				output.write("\nProcess Completed in " + seconds + " seconds.");
		} finally {
			output.close();
		}
	}
	
}