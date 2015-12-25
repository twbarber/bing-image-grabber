package jig.bing;

import jig.constants.AdultOption;
import jig.constants.Market;
import jig.util.StringUtils;

/**
 * Object generated from the QueryBuilder. Contains full list of parameters
 * to pass to the Bing Search API.
 */
public class QueryParameters {

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

}
