package jig.bing;

import jig.constants.AdultOption;
import jig.constants.Market;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Object generated from the QueryBuilder. Contains full list of parameters
 * to pass to the Bing Search API.
 */
public class QueryParameters {

  private Logger logger = Logger.getLogger(QueryParameters.class);
  private String searchTerm;
  private int numberOfImages;
  private AdultOption adultOption;
  private Market market;

  public QueryParameters(String searchTerm, int numberOfImages, AdultOption adultOption, Market market) {
    this.searchTerm = searchTerm;
    this.numberOfImages = numberOfImages;
    this.adultOption = adultOption;
    this.market = market;
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public int getNumberOfImages() {
    return numberOfImages;
  }

  public AdultOption getAdultOption() {
    return adultOption;
  }

  public Market getMarket() {
    return market;
  }

  public String getEncodedSearchTerm() {
    return "&Query=%27" + encodeParameter(searchTerm) + "%27";
  }

  public String getEncodedNumberOfImages() {
    return  "&$top=" + String.valueOf(numberOfImages);
  }

  public String getEncodedAdultOption() {
    return "&Adult=%27" + this.adultOption.toString() + "%27";
  }

  public String getEncodedMarket() {
    return "&Market=" + market.toString();
  }

  private String encodeParameter(String toEncode) {
    try {
       return URLEncoder.encode(toEncode, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException("Bad Argument: " + toEncode);
    }
  }
}
