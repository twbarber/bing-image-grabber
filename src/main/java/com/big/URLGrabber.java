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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class URLGrabber {

	private String encryptedKey;
	
	public URLGrabber(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}

	public String runQuery(URL aQueryURL) throws Exception {
		URLConnection urlConnection = aQueryURL.openConnection();
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
			URL mediaURL = new URL(resObject.get("MediaUrl").getAsString());
			parsedURLs.add(mediaURL);
		}
		
		return parsedURLs;
	}
}