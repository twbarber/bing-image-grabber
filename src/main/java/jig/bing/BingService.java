package jig.bing;
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
 	
 		Example: ImageRequest - 456123
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
import jig.config.Config;
import jig.config.KeyHandler;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

/**
 * BingService acts as the messenger for the Bing Search API. Returns ImageResult
 * Objects to the caller for future use.
 */
public class BingService {

  private Logger logger = Logger.getLogger(BingService.class);
  private Config config;

  public BingService(Config config) {
    this.config = config;
  }

  public String search(ImageRequest request) throws Exception {
    URLConnection urlConnection = request.getRequestUrl().openConnection();
    String authKey = "Basic " + this.config.getAccountKey();
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

  public void downloadImages(Collection<URL> imagesToDownload) {
    ImageDownloader imageDownloader = new ImageDownloader();
    imageDownloader.saveImages(imagesToDownload);
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

  private void authenticate() {
    KeyHandler keyHandler = new KeyHandler();
    try {
      String userKey = keyHandler.getExistingKey();
      while (keyHandler.isValidKey(userKey)) {
        userKey = this.config.getAccountKey();
        if (keyHandler.isValidKey(userKey)) {
          keyHandler.writeKey(userKey);
          return;
        }
      }
    } catch (IOException e) {
      System.err.println("There was an error authenticating your key.");
    }
  }

}

