package jig.config;

import jig.constants.AdultOption;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Used for storing configuration information for the Java Image Grabber.
 *
 * Stores the users AccountKey and other preferences that can be written
 * to disk for use at a later time.
 */
public class Config {

  private Logger logger = Logger.getLogger(Config.class);
  private final String accountKey;

  public Config(File file) {
    this.apiKey = loadAccountKey(file);
    loadDefaults();
  }

  private String loadAccountKey(File file) {

  }

  private void loadDefaults() {

  }

  public String getApiKey() {
    return this.apiKey;
  }

}