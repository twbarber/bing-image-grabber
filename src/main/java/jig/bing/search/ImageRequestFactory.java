package jig.bing.search;

import java.util.Random;
import jig.bing.enums.AdultOption;
import org.apache.log4j.Logger;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class ImageRequestFactory {

  private static Logger logger = Logger.getLogger(ImageRequestFactory.class);
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
    return new ImageRequest(parameters);
  }

  private String generateRandomSearchTerm() {
    Random rand = new Random();
    int max = 9999999;
    int min = 1000000;
    int randomTerm = rand.nextInt((max - min) + 1 + min);
    return String.valueOf(randomTerm);
  }

}
