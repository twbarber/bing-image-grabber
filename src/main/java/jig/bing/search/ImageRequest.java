package jig.bing.search;

import org.apache.log4j.Logger;

/**
 * Wrapper Object for Bing Image Request and it's Parameters.
 */
public class ImageRequest {

  private Logger logger = Logger.getLogger(ImageRequest.class);
  private final String API_BASE = "https://api.datamarket.azure.com/Bing/ImageRequest/Image?";
  private final String DEFAULT_FORMAT = "$format=JSON";
  private ImageRequestParameters parameters;
  private String requestUrl;

  public ImageRequest(ImageRequestParameters parameters) {
    this.parameters = parameters;
    this.requestUrl = generateRequestUrl(parameters);
  }

  public String generateRequestUrl(ImageRequestParameters parameters) {
    logger.info("Building request with parameters: " + parameters.toString());
    return API_BASE
          + DEFAULT_FORMAT
          + parameters.getEncodedSearchTerm()
          + parameters.getEncodedAdultOption()
          + parameters.getEncodedNumberOfImages();
  }

  public ImageRequestParameters getParameters() {
    return parameters;
  }

  public String getRequestUrl() {
    return requestUrl;
  }

}
