package jig.config;

import jig.constants.AdultOption;
import org.apache.log4j.Logger;

/**
 * Used for storing configuration information for the Java Image Grabber.
 *
 * Stores the users AccountKey and other preferences that can be written
 * to disk for use at a later time.
 */
public class Config {

  private Logger logger = Logger.getLogger(Config.class);
  private final String apiKey;

  public Config(String apiKey) {
    this.apiKey = apiKey;
    loadDefaults();
  }

  private void loadDefaults() {

  }

  public String getApiKey() {
    return this.apiKey;
  }

  class ConfigDefaults {

    public int IMAGE_COUNT = 20;
    public AdultOption ADULT_OPTION = AdultOption.MODERATE;
    public String CONFIG_DIR = "";

  }
}