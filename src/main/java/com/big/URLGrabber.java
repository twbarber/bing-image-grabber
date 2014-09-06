package com.big;
/* 
	urlGrabber generates a JSON object with results from the Bing search, which
	we parse to extract the URLs of images. After that, they are downloaded
	and stored locally.

	Some of the code was used from the GitHub:

	https://github.com/mark-watson/bing_search_java/

	A query URL requires the following components:

		- Root Bing API URL, constant. See "rootURL" in makeQuery();
		- User's API Key, entered in test class
		- Search Term, we use a randomly generated integer
		- Query Type (Web, Images*, Video, etc.)

	The following parameters are optional, but we add them for completeness

		- Query Region, set as en-us for our searches
		- Content Filter, so no adult content... hopefully
		- Number of images per request.

 *In our case the search type is a constant, Images.

	The URL returns a JSON object which we parse to grab URLs of images that are
	returned using the search, The URLs are stored in an String array, and are then
	accessed by the saveImages() method. The size of the array is equal to the
	number of images requested per search term (imageCount parameter in the constructor).
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class URLGrabber {

	public String encryptedKey;
	public String[] parsedURLs; 	// Array of parsed URLs from JSON object
	public String[] bingURLs;
	
	public URLGrabber(String encryptedKey, String[] bingURLs) {
		
		this.encryptedKey = encryptedKey;
		this.bingURLs = bingURLs.clone();
	}
	
	// Encompasses all the methods we need to create a folder full of images.
	// Makes the test class super easy to write.
	public void run() throws Exception {
		
		for(int i = 0; i < bingURLs.length; i++) {
			String queryURL = bingURLs[i];
			String jsonLine = runQuery(queryURL);		// Pulls JSON string from URL
			parseURLs(jsonLine);				// Parses URL from JSON string
		}
	}

	// This method is largely based on Mark Watson's wrapper found at the GitHub repo mentioned
	// above. Put the code in a method for my own organizational structure.
	// Creates URL object based on the String generated from users parameters, and opens an URL 
	// connection to that address. That connections is then passed the authentication we encrypted
	// when this serialGrabber object was created. The resulting JSON string is passed into a string
	// called sb, which is returned to the run method to be used as a parameter for parsing.
	public String runQuery(String queryURL) throws Exception {

		URL url = new URL(queryURL);
		URLConnection urlConnection = url.openConnection();
		String s1 = "Basic " + this.encryptedKey;
		urlConnection.setRequestProperty("Authorization", s1);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		String inputLine;
		StringBuffer sb = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine);
		in.close();
		return sb.toString();
	}

	// Parameter is JSON string returned by Bing
	// GSON used as library for parsing
	public void parseURLs(String jsonLine) {

		JsonParser jsonParser = new JsonParser();					// New Parser
		JsonArray results = jsonParser.parse(jsonLine)				// Parses JSON into "results" array
				.getAsJsonObject().get("d").getAsJsonObject()
				.getAsJsonArray("results");
		// Iterator for Array, puts URL in each index
		int i = 0;												
		for(JsonElement result : results) {
			JsonObject resObject = result.getAsJsonObject();	// Turns result element into Object
			String MediaUrl = resObject.get("MediaUrl").getAsString(); // Grabs MediaUrl field from object
			this.parsedURLs[i] = MediaUrl;					// Puts string val of MediaUrl in array
			i++;
		}
	}
}