package jig.bing.search;

import jig.bing.enums.AdultOption;
import jig.bing.enums.Market;

/**
 * Object generated from the QueryBuilder. Contains full list of parameters
 * to pass to the Bing ImageRequest API.
 */
public class ImageRequestParameters {

  private String searchTerm;
  private int numberOfImages;
  private AdultOption adultOption;
  private Market market;

  public ImageRequestParameters(String searchTerm, int numberOfImages, AdultOption adultOption,
      Market market) {
    this.searchTerm = searchTerm;
    this.numberOfImages = numberOfImages;
    this.adultOption = adultOption;
    this.market = market;
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public int getNumberOfImages() { return numberOfImages; }

  public AdultOption getAdultOption() {
    return adultOption;
  }

  public Market getMarket() {
    return this.market;
  }

  public String toString() {
    return "ImageRequest Term: " + getSearchTerm() + "\n"
          + "Number Of Images: " + getNumberOfImages() + "\n"
          + "Adult Option: " + getAdultOption() + "\n"
          + "Market: " + getMarket();
  }
}
