package jig.bing;

import jig.constants.AdultOption;
import jig.util.StringUtils;
import org.apache.log4j.Logger;

import java.net.URL;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class ImgeRequestFactory {

  private static Logger logger = Logger.getLogger(ImgeRequestFactory.class);
  private static final String ROOT_URL = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON";
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

  public ImageRequest createRequest(String searchTerm, int numberOfImages) {
    ImageRequestParameters parameters =
        new ImageRequestParameters(searchTerm, numberOfImages, DEFAULT_ADULT_OPTION);
    return new ImageRequest(parameters, generateRequestUrl(parameters));
  }

  public ImageRequest createRequest(String searchTerm, AdultOption adultOption) {
    ImageRequestParameters parameters =
        new ImageRequestParameters(searchTerm, DEFAULT_COUNT, adultOption);
    return new ImageRequest(parameters, generateRequestUrl(parameters));
  }

  public ImageRequest createRequest(String searchTerm) {
    ImageRequestParameters parameters =
        new ImageRequestParameters(searchTerm, DEFAULT_COUNT, DEFAULT_ADULT_OPTION);
    return new ImageRequest(parameters, generateRequestUrl(parameters));
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
