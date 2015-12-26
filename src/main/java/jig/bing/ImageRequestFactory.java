package jig.bing;

import jig.constants.AdultOption;
import jig.util.StringUtils;
import org.apache.log4j.Logger;

import java.net.URL;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class ImageRequestFactory {

  private static Logger logger = Logger.getLogger(ImageRequestFactory.class);
  private static final String ROOT_URL = "https://api.datamarket.azure.com/Bing/Search/Image?";
  private int DEFAULT_COUNT = 50;
  private AdultOption DEFAULT_ADULT_OPTION = AdultOption.STRICT;

	public URL generateRequestUrl(ImageRequestParameters parameters) {
    logger.info("Building query with parameters: " + parameters.toString());
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append(ROOT_URL);
    queryBuilder.append(parameters.getEncodedSearchTerm());
    queryBuilder.append(parameters.getEncodedAdultOption());
    queryBuilder.append(parameters.getEncodedNumberOfImages());
    return StringUtils.convertToUrl(queryBuilder.toString());
	}

  public ImageRequest createRequest() {
    return createRequest(generateRandomSearchTerm(), DEFAULT_COUNT, DEFAULT_ADULT_OPTION);
  }

  public ImageRequest createRequest(String searchTerm) {
    return createRequest(searchTerm, DEFAULT_COUNT, DEFAULT_ADULT_OPTION);
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

  public ImageRequest createRequest(String searchTerm, int numberOfImages, AdultOption adultOption) {
    ImageRequestParameters parameters =
        new ImageRequestParameters(searchTerm, numberOfImages, adultOption);
    return new ImageRequest(parameters, generateRequestUrl(parameters));
  }

  private String generateRandomSearchTerm() {
    return String.valueOf(Math.random() * 1000000 + 1000000);
  }

}
