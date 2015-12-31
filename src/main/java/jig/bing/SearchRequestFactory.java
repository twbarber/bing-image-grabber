package jig.bing;

import java.net.URL;
import java.util.Random;
import jig.bing.enums.AdultOption;
import jig.util.StringUtils;
import org.apache.log4j.Logger;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class SearchRequestFactory {

  private static Logger logger = Logger.getLogger(SearchRequestFactory.class);
  private final String API_BASE = "https://api.datamarket.azure.com/Bing/SearchRequest/Image?";
  private final String DEFAULT_FORMAT = "$format=JSON";
  private int DEFAULT_COUNT = 50;
  private AdultOption DEFAULT_ADULT_OPTION = AdultOption.STRICT;

  public SearchRequest createRequest() {
    return createRequest(generateRandomSearchTerm(), DEFAULT_COUNT, DEFAULT_ADULT_OPTION);
  }

  public SearchRequest createRequest(String searchTerm) {
    return createRequest(searchTerm, DEFAULT_COUNT, DEFAULT_ADULT_OPTION);
  }

  public SearchRequest createRequest(AdultOption adultOption) {
    return createRequest(generateRandomSearchTerm(), DEFAULT_COUNT, adultOption);
  }

  public SearchRequest createRequest(int numberOfImages) {
    return createRequest(generateRandomSearchTerm(), numberOfImages, DEFAULT_ADULT_OPTION);
  }

  public SearchRequest createRequest(String searchTerm, int numberOfImages) {
    return createRequest(searchTerm, numberOfImages, DEFAULT_ADULT_OPTION);
  }

  public SearchRequest createRequest(String searchTerm, AdultOption adultOption) {
    return createRequest(searchTerm, DEFAULT_COUNT, adultOption);
  }

  public SearchRequest createRequest(int numberOfImages, AdultOption adultOption) {
    return createRequest(generateRandomSearchTerm(), numberOfImages, adultOption);
  }

  public SearchRequest createRequest(String searchTerm, int numberOfImages,
      AdultOption adultOption) {
    SearchRequestParameters parameters =
        new SearchRequestParameters(searchTerm, numberOfImages, adultOption);
    return new SearchRequest(parameters, generateRequestUrl(parameters));
  }

  public URL generateRequestUrl(SearchRequestParameters parameters) {
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
