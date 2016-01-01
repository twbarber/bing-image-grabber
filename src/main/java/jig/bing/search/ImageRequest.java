package jig.bing.search;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import jig.bing.enums.Market;
import org.apache.log4j.Logger;

/**
 * Wrapper Object for Bing Image Request and it's Parameters.
 */
public class ImageRequest {

  private Logger logger = Logger.getLogger(ImageRequest.class);
  private final String API_BASE = "https://api.datamarket.azure.com/Bing/ImageRequest/Image?";
  private final String DEFAULT_FORMAT = "JSON";
  private final String EMPTY_MARKET_STRING = "";
  private final String QUERY = "Query=%%27%s%%27";
  private final String ADULT = "&Adult=%%27%s%%27";
  private final String MARKET = "&Market=%%27%s%%27";
  private final String TOP = "&$top=%d";
  private final String FORMAT = "&$format=%s";

  private ImageRequestParameters parameters;
  private String requestUrl;

  public ImageRequest(ImageRequestParameters parameters) {
    this.parameters = parameters;
    this.requestUrl = generateRequestUrl(parameters);
  }

  public String generateRequestUrl(ImageRequestParameters parameters) {
    logger.info("Building request with parameters: " + parameters.toString());
    return API_BASE
          + String.format(QUERY, encode(parameters.getSearchTerm()))
          + String.format(ADULT, parameters.getAdultOption().toString())
          + String.format(TOP, parameters.getNumberOfImages())
          + String.format(FORMAT, DEFAULT_FORMAT)
          + addMarketIfSpecified(parameters.getMarket());
  }

  private String addMarketIfSpecified(Market market) {
    if (!market.equals(Market.NONE)) {
      return String.format(MARKET, market.toString());
    } else return EMPTY_MARKET_STRING;
  }

  public String getRequestUrl() {
    return requestUrl;
  }

  private String encode(String toEncode) {
    try {
      return URLEncoder.encode(toEncode, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException("Bad Argument in URL: " + toEncode);
    }
  }

  public ImageRequestParameters getParameters() {
    return parameters;
  }

}
