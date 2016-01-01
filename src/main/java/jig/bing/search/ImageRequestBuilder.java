package jig.bing.search;

import java.util.Random;
import jig.bing.enums.AdultOption;
import jig.bing.enums.Market;
import org.apache.log4j.Logger;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class ImageRequestBuilder {

  private static Logger logger = Logger.getLogger(ImageRequestBuilder.class);
  private int DEFAULT_IMAGE_NUMBER = 50;
  private AdultOption DEFAULT_ADULT_OPTION = AdultOption.STRICT;
  private Market DEFAULT_MARKET = Market.NONE;
  private String searchTerm;

  private int numberOfImages;
  private AdultOption adultOption;
  private Market market;

  public ImageRequestBuilder() {
    this.searchTerm = generateRandomSearchTerm();
    this.numberOfImages = DEFAULT_IMAGE_NUMBER;
    this.adultOption = DEFAULT_ADULT_OPTION;
    this.market = DEFAULT_MARKET;
  }

  public ImageRequest build() {
    ImageRequestParameters parameters =
        new ImageRequestParameters(getSearchTerm(), getNumberOfImages(),
            getAdultOption(), getMarket());
    return new ImageRequest(parameters);
  }

  private String generateRandomSearchTerm() {
    Random rand = new Random();
    int max = 9999999;
    int min = 1000000;
    int randomTerm = rand.nextInt((max - min) + 1 + min);
    return String.valueOf(randomTerm);
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public void setSearchTerm(String searchTerm) {
    this.searchTerm = searchTerm;
  }

  public int getNumberOfImages() {
    return numberOfImages;
  }

  public void setNumberOfImages(int numberOfImages) {
    this.numberOfImages = numberOfImages;
  }

  public AdultOption getAdultOption() {
    return adultOption;
  }

  public void setAdultOption(AdultOption adultOption) {
    this.adultOption = adultOption;
  }

  public Market getMarket() {
    return market;
  }

  public void setMarket(Market market) {
    this.market = market;
  }

}
