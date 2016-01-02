package jig.bing;

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
import jig.bing.search.ImageResponse;
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

  public ImageResponse search(ImageRequest request) throws Exception {
    this.logger.info("Executing search: " + request.toString());
    Request searchRequest = new Request.Builder()
        .url(request.getRequestUrl())
        .build();
    Response response = client.newCall(searchRequest).execute();
    return getImageResponse(response);
  }

  private ImageResponse getImageResponse(Response response) {
    Gson gson = new Gson();
    JsonParser parser = new JsonParser();
    JsonObject jsonResponse = parser.parse(response.toString())
        .getAsJsonObject()
        .get("d")
        .getAsJsonObject();
    JsonArray results = jsonResponse.getAsJsonArray("results");
    Collection<ImageResult> imageResults = new ArrayList<>();
    for (JsonElement result : results) {
      ImageResult anImageResult = gson.fromJson(result, ImageResult.class);
      imageResults.add(anImageResult);
    }
    return new ImageResponse(imageResults);
  }

  public void downloadImages(ImageResponse response) {
    ImageDownloader imageDownloader = new ImageDownloader();
    imageDownloader.saveImages(response.getResults());
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

