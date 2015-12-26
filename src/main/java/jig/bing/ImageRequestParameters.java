package jig.bing;

import jig.constants.AdultOption;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Object generated from the QueryBuilder. Contains full list of parameters
 * to pass to the Bing Search API.
 */
public class ImageRequestParameters {

  private String searchTerm;
  private int numberOfImages;
  private AdultOption adultOption;

  public ImageRequestParameters(String searchTerm, int numberOfImages, AdultOption adultOption) {
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
    StringBuilder builder = new StringBuilder();
    builder.append("Search Term: ");
    builder.append(searchTerm);
    builder.append("\nNumber Of Images: ");
    builder.append(numberOfImages);
    builder.append("\nAdult Option: ");
    builder.append(adultOption);
    return builder.toString();
  }
}
