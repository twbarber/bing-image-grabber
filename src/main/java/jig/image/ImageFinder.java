package jig.image;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jig.constants.AdultOption;
import jig.constants.Market;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

public class ImageFinder {
	
	private ArrayList<URL> imageURLs = new ArrayList<URL>();
	private String rawQueryTerm;		
	private File queryDirectory;			
	
	/**
	 * 
	 * 
	 */
	public ImageFinder(Collection<URL> parsedUrls) {
		this.imageURLs.addAll(parsedUrls);
		this.queryDirectory = makeDirectories();
		try {
			downloadImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates directories in <user_home>/jig/images/<query_term> where images will be
	 * saved
	 * 
	 * @return Directory where images where be saved. 
	 */
	public File makeDirectories() {
		String userHome = System.getProperty("user.home");
		File imageDirectory = new File(userHome + "/grabber/images");
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
		System.out.print("\nDownloading Images");	
		long startTime = System.nanoTime();
		int imageIndex = 0;

		for(URL imageURL : imageURLs) {						
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

				is.close();
				os.close();

				imageCount++;			
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
		
		for(URL imageURL : this.imageURLs) {
			output.write(imageURL.toString() + "\n");
		}
		output.write("\nProcess Completed in " + seconds + " seconds.");
		output.close();	
	}

	public Collection<URL> getImageUrls(String searchTerm) {
		return null;
	}

	public Collection<URL> getImageUrls(String searchTerm, int numberOfImages) {
		return null;
	}

	public Collection<URL> getImageUrls(String searchTerm, int numberOfImages, AdultOption adultOption) {
		return null;
	}

	public Collection<URL> getImageUrls(String searchTerm, int numberOfImages, AdultOption adultOption, Market market) {
		return null;
	}

	private String encryptedKey;

	public String runQuery(URL aQueryUrl) throws Exception {
		URLConnection urlConnection = aQueryUrl.openConnection();
		String authKey = "Basic " + this.encryptedKey;
		urlConnection.setRequestProperty("Authorization", authKey);
		BufferedReader responseReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine = responseReader.readLine();
		StringBuffer jsonString = new StringBuffer();

		while (inputLine != null) {
			jsonString.append(inputLine);
			inputLine = responseReader.readLine();
		}

		return jsonString.toString();
	}

	public Collection<URL> parseURLs(String jsonLine) throws MalformedURLException {
		ArrayList<URL> parsedURLs = new ArrayList<>();
		JsonParser jsonParser = new JsonParser();
		JsonArray results = jsonParser.parse(jsonLine).getAsJsonObject().get("d").getAsJsonObject()
				.getAsJsonArray("results");

		for (JsonElement result : results) {
			JsonObject resObject = result.getAsJsonObject();
			URL mediaUrl = new URL(resObject.get("MediaUrl").getAsString());
			parsedURLs.add(mediaUrl);
		}

		return parsedURLs;
	}

	private Collection<URL> getImageUrlList(Collection<URL> bingURLs) {
		ArrayList<URL> imageURLList = new ArrayList<URL>();
		for (URL aBingURL : bingURLs) {
			try {
				String jsonAsString = runQuery(aBingURL);
				imageURLList.addAll(parseURLs(jsonAsString));
			} catch (Exception e) {
				System.err.println("There was a problem getting the image URLs.");
			}
		}
		return imageURLList;
	}


}