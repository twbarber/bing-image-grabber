package com.big;
/**
 * imageDownloader class that handles the actual retrieval of images.
 	
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
import java.util.Collection;

public class ImageDownloader {
	
	private ArrayList<URL> imageURLs = new ArrayList<URL>();
	private String rawQueryTerm;		
	private File queryDirectory;			
	
	/**
	 * 
	 * 
	 */
	public ImageDownloader(Collection<URL> parsedURLs) {
		this.imageURLs.addAll(parsedURLs);
		this.queryDirectory = makeDirectories();
		try {
			downloadImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates directories in <user_home>/big/images/<query_term> where images will be 
	 * saved
	 * 
	 * @return Directory where images where be saved. 
	 */
	public File makeDirectories() {
		String userHome = System.getProperty("user.home");
		File imageDirectory = new File(userHome + "/big/images");
		File queryDirectory = new File(imageDirectory + "/" + this.rawQueryTerm);
		
		if (!imageDirectory.exists()) {
			if (imageDirectory.mkdir())
				System.out.println("\nBing Directory: " + imageDirectory.toString() + " created");
		}
		if (!queryDirectory.exists()) {
			if (queryDirectory.mkdir())
				System.out.println("\nQuery Directory: " + queryDirectory.toString() + " created");
		}
		return queryDirectory;
	}
	
	/**
	 * 
	 * 
	 * @throws IOException
	 */
	public void downloadImages() throws IOException {

		int imageCount = 0; 											
		
		// Lets user know downloading started, starts timer
		System.out.print("\nDownloading Images");	
		long startTime = System.nanoTime();
		int imageIndex = 0;
		// Loops over all URLs in array
		for(URL imageURL : imageURLs) {						
			// Lets user know there's progress
			System.out.print(".");					
			try {								
				String fileName = this.queryDirectory + "/img" + imageIndex; 				
				File img = new File(fileName);
				InputStream is = imageURL.openStream();			
				
				OutputStream os = new FileOutputStream(img.toString());

				byte[] b = new byte[2048];
				int length;

				while ((length = is.read(b)) != -1) {
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
	
	/**
	 * 
	 * @param seconds
	 * @throws IOException
	 */
	public void makeLog(Double seconds) throws IOException {
		
		File logFile = new File(queryDirectory + "/" + rawQueryTerm + ".txt");
		Writer output = new BufferedWriter(new FileWriter(logFile));
		try {
			for(URL imageURL : this.imageURLs)
				output.write(imageURL.toString() + "\n");
				output.write("\nProcess Completed in " + seconds + " seconds.");
		} finally {
			output.close();
		}
	}
	
}