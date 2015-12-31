package jig.bing;

import java.net.URL;
import java.util.Random;
import jig.constants.AdultOption;
import jig.util.StringUtils;
import org.apache.log4j.Logger;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class ImageRequestFactory {

  private static Logger logger = Logger.getLogger(ImageRequestFactory.class);
  private final String API_BASE = "https://api.datamarket.azure.com/Bing/Search/Image?";
  private final String DEFAULT_FORMAT = "$format=JSON";
  private int DEFAULT_COUNT = 50;
  private AdultOption DEFAULT_ADULT_OPTION = AdultOption.STRICT;

  public ImageRequest createRequest() {
    return createRequest(generateRandomSearchTerm(), DEFAULT_COUNT, DEFAULT_ADULT_OPTION);
  }

  public ImageRequest createRequest(String searchTerm) {
    return createRequest(searchTerm, DEFAULT_COUNT, DEFAULT_ADULT_OPTION);
  }

  public ImageRequest createRequest(AdultOption adultOption) {
    return createRequest(generateRandomSearchTerm(), DEFAULT_COUNT, adultOption);
  }

  public ImageRequest createRequest(int numberOfImages) {
    return createRequest(generateRandomSearchTerm(), numberOfImages, DEFAULT_ADULT_OPTION);
  }

  public ImageRequest createRequest(String searchTerm, int numberOfImages) {
    return createRequest(searchTerm, numberOfImages, DEFAULT_ADULT_OPTION);
  }

  public ImageRequest createRequest(String searchTerm, AdultOption adultOption) {
    return createRequest(searchTerm, DEFAULT_COUNT, adultOption);
  }

  public ImageRequest createRequest(int numberOfImages, AdultOption adultOption) {
    return createRequest(generateRandomSearchTerm(), numberOfImages, adultOption);
  }

  public ImageRequest createRequest(String searchTerm, int numberOfImages,
      AdultOption adultOption) {
    ImageRequestParameters parameters =
        new ImageRequestParameters(searchTerm, numberOfImages, adultOption);
    return new ImageRequest(parameters, generateRequestUrl(parameters));
  }

  public URL generateRequestUrl(ImageRequestParameters parameters) {
    logger.info("Building query with parameters: " + parameters.toString());
    String search = API_BASE
      + DEFAULT_FORMAT
      + parameters.getEncodedSearchTerm()
      + parameters.getEncodedAdultOption()
      + parameters.getEncodedNumberOfImages();
    return StringUtils.convertToUrl(search);
  }

  private String generateRandomSearchTerm() {
    Random rand = new Random();
    int max = 9999999;
    int min = 1000000;
    int randomTerm = rand.nextInt((max - min) + 1 + min);
    return String.valueOf(randomTerm);
  }

}
