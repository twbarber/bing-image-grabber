/*
 	Bing Image Grabber - Version 0.1.0
 	
 	Image downloader class that handles the actual retrieval of images.
 	
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

import java.io.*;
import java.util.*;
import java.net.URL;

public class imageDownloader {
	
	public String[] imageURLs;		// Array of parsed URLs
	public String rawQueryTerm;		// Query for directory organization
	public String queryDir;			// Storage Directory
	
	// Constructor copies the imageURL array into this object.
	// It also copies in the raw query for directory structure
	public imageDownloader(urlGrabber myUrlGrabber) {
		this.imageURLs = new String[myUrlGrabber.storedAddresses.length];
		for(int i = 0; i < imageURLs.length; i++)
			imageURLs[i] = myUrlGrabber.storedAddresses[i];
		this.rawQueryTerm = myUrlGrabber.rawQueryTerm;
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
		
		Scanner in = new Scanner(System.in);
		System.out.print("\nEnter Storage Directory: ");
		String strDirectory = in.next();
		in.close();
		File bingDir = new File(strDirectory);
		File queryDir = new File(strDirectory + "/" + this.rawQueryTerm);
		
		if (!bingDir.exists()) {
			boolean success = bingDir.mkdir();
			if (success)
				System.out.println("\nBing Directory: " + strDirectory + " created");
		}
		
		if (!queryDir.exists()) {
			boolean success = queryDir.mkdir();
			if (success)
				System.out.println("Query Directory: " + queryDir.toString() + " created");
		}
		
		return queryDir.toString();
	}
	
	// Saves images from array of URLs locally. 
	// 
	public void download() throws IOException {
		// Counts downloaded images
		int imageCount = 0; 											
		
		// Lets user know downloading started, starts timer
		System.out.print("\nDownloading Images");	
		long startTime = System.nanoTime();
		
		// Loops over all URLs in array
		for(int i = 0; i < imageURLs.length; i++) {						
			
			// Lets user know there's progress
			System.out.print(".");					
			// Try - Catch to make sure URL is good
			try {								
				// Builds string for img file name
				String fileName = queryDir + "/img" + i; 				
				File img = new File(fileName);
				URL url = new URL(imageURLs[i]);						// Creates URL object from URL String
				InputStream is = url.openStream();						// Opens input stream on image at end of URL
			
			OutputStream os = new FileOutputStream(img.toString());		// Opens writer to img file

			byte[] b = new byte[2048];
			int length;
			
			// Loops to the end of the file
			while ((length = is.read(b)) != -1) {
				//Writes length bytes from the specified byte array b starting at offset 0 to this output stream.
				os.write(b, 0, length);
			}

			is.close();					// Close inputStream
			os.close();					// Close outputStream
			
			imageCount++;				// Increments for final download count.
			} catch (IOException e) {
			}
			
		}
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double seconds = duration / 1000000000.0; // Converts time to download all images to seconds
		
		System.out.printf("\n\nSaved %d images in %.2f seconds.", imageCount, seconds);
	}
}