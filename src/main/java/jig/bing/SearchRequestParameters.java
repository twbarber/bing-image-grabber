package jig.bing;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import jig.bing.enums.AdultOption;

/**
 * Object generated from the QueryBuilder. Contains full list of parameters
 * to pass to the Bing SearchRequest API.
 */
public class SearchRequestParameters {

  private String searchTerm;
  private int numberOfImages;
  private AdultOption adultOption;

  public SearchRequestParameters(String searchTerm, int numberOfImages, AdultOption adultOption) {
    this.searchTerm = searchTerm;
    this.numberOfImages = numberOfImages;
    this.adultOption = adultOption;
  }

  private String encodeParameter(String toEncode) {
    try {
      return URLEncoder.encode(toEncode, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException("Bad Argument: " + toEncode);
    }
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public int getNumberOfImages() { return numberOfImages; }

  public AdultOption getAdultOption() {
    return adultOption;
  }

  public String getEncodedSearchTerm() {
    return "&Query=%27" + encodeParameter(searchTerm) + "%27";
  }

  public String getEncodedNumberOfImages() {
    return "&$top=" + String.valueOf(numberOfImages);
  }

  public String getEncodedAdultOption() {
    return "&Adult=%27" + this.adultOption.toString() + "%27";
  }

  public String toString() {
    return "SearchRequest Term: " + searchTerm + "\n"
          + "Number Of Images: " + numberOfImages + "\n"
          + "Adult Option: " + adultOption;
  }
}
