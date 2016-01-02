package jig.bing;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import jig.bing.image.ImageResult;
import jig.bing.search.ImageResponse;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ImageDownloaderTest {

  private ImageDownloader serviceUnderTest = new ImageDownloader();
  private ImageResponse testImageResponse;

  @Before
  public void setup() {
    Collection<ImageResult> imageResults = loadImageResponseFromJson();
    this.testImageResponse = new ImageResponse(imageResults);
  }

  public Collection<ImageResult> loadImageResponseFromJson() {
   Collection<ImageResult> imageResults = new ArrayList<>();
   try {
     InputStream configStream =
         ClassLoader.getSystemClassLoader().getResourceAsStream("test_response.json");
     String testJson = IOUtils.toString(configStream);
         JsonParser parser = new JsonParser();
     JsonObject jsonResponse = parser.parse(testJson)
         .getAsJsonObject()
         .get("d")
         .getAsJsonObject();
     JsonArray jsonImages = jsonResponse.getAsJsonArray("results");
     Gson gson = new Gson();
     for (JsonElement result : jsonImages) {
       ImageResult anImageResult = gson.fromJson(result, ImageResult.class);
       imageResults.add(anImageResult);
     }
   } catch (IOException e) {
     System.err.print("Couldn't load test_response.json");
   }
   return imageResults;
  }

  @Test
  public void testHaveImageResults() {
    assertTrue(this.testImageResponse.getResults() != null);
    assertTrue(!this.testImageResponse.getResults().isEmpty());
  }

	@Test
  @Ignore
	public void testImageDownloader() {
		Collection<BufferedImage> downloadedImages =
        this.serviceUnderTest.downloadImages(testImageResponse.getResults());
    assertTrue(!downloadedImages.isEmpty());
  }



}
