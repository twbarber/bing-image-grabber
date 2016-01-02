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
    loadDefaults();
  }

  public ImageRequest buildRequest() {
    ImageRequestParameters parameters =
        new ImageRequestParameters(getSearchTerm(), getNumberOfImages(),
            getAdultOption(), getMarket());
    return new ImageRequest(parameters);
  }

  public ImageRequestBuilder clear() {
    return loadDefaults();
  }

  private ImageRequestBuilder loadDefaults() {
    this.searchTerm = generateRandomSearchTerm();
    this.numberOfImages = DEFAULT_IMAGE_NUMBER;
    this.adultOption = DEFAULT_ADULT_OPTION;
    this.market = DEFAULT_MARKET;
    return this;
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

  public ImageRequestBuilder setSearchTerm(String searchTerm) {
    this.searchTerm = searchTerm;
    return this;
  }

  public int getNumberOfImages() {
    return numberOfImages;
  }

  public ImageRequestBuilder setNumberOfImages(int numberOfImages) {
    this.numberOfImages = numberOfImages;
    return this;
  }

  public AdultOption getAdultOption() {
    return adultOption;
  }

  public ImageRequestBuilder setAdultOption(AdultOption adultOption) {
    this.adultOption = adultOption;
    return this;
  }

  public Market getMarket() {
    return market;
  }

  public ImageRequestBuilder setMarket(Market market) {
    this.market = market;
    return this;
  }

}
