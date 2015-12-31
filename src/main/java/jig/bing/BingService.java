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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import jig.bing.image.ImageResult;
import jig.bing.search.ImageRequest;
import jig.config.Config;
import org.apache.log4j.Logger;

/**
 * BingService acts as the messenger for the Bing ImageRequest API. Returns ImageResult
 * Objects to the caller for future use.
 */
public class BingService {

  private Logger logger = Logger.getLogger(BingService.class);
  private OkHttpClient client = new OkHttpClient();
  private Config config;

  public BingService(final Config config) {
    this.config = config;
    this.client.setAuthenticator(new BingAuthenticator());
  }

  public Collection<ImageResult> search(ImageRequest request) throws Exception {
    Request searchRequest = new Request.Builder()
        .url(request.getRequestUrlAsString())
        .build();
    Response response = client.newCall(searchRequest).execute();
    return getImageResults(response.body().string());
  }

  private Collection<ImageResult> getImageResults(String response) {
    System.out.print(response);
    Gson gson = new Gson();
    JsonParser parser = new JsonParser();
    JsonObject jsonResponse = parser.parse(response)
        .getAsJsonObject()
        .get("d")
        .getAsJsonObject();
    JsonArray results = jsonResponse.getAsJsonArray("results");
    Collection<ImageResult> imageResults = new ArrayList<>();
    for (JsonElement result : results) {
      ImageResult anImageResult = gson.fromJson(result, ImageResult.class);
      imageResults.add(anImageResult);
    }
    System.out.println(imageResults);
    return imageResults;
  }

  public void downloadImages(Collection<ImageResult> imagesToDownload) {
    ImageDownloader imageDownloader = new ImageDownloader();
    imageDownloader.saveImages(imagesToDownload);
  }

  private class BingAuthenticator implements Authenticator {

    @Override
    public Request authenticate(Proxy proxy, Response response) throws IOException {
      String credential = Credentials.basic("", config.getAccountKey().getAccountKey());
      return response.request().newBuilder()
          .header("Authorization", credential)
          .build();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
      String credential = Credentials.basic("", config.getAccountKey().getAccountKey());
      return response.request().newBuilder()
          .header("Proxy-Authorization", credential)
          .build();
    }
  }

}

