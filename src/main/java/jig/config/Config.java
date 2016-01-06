package jig.config;

import java.io.File;
import org.apache.log4j.Logger;

/**
 * Used for storing configuration information for the Java Image Grabber.
 *
 * Stores the users AccountKey and other preferences that can be written
 * to disk for use at a later time.
 */
public class Config {

  private Logger logger = Logger.getLogger(Config.class);
  private final String accountKey;

  public Config(String accountKey) {
    this.accountKey = accountKey;
    loadDefaults();
    validate();
  }

  private String loadAccountKey(File file) {
    return "";
  }

  private void loadDefaults() {

  }

  public String getAccountKey() {
    return this.accountKey;
  }

  private void validate() {
    if (this.getAccountKey() == null || this.getAccountKey().isEmpty()) {
      throw new IllegalArgumentException("Bing Account Key cannot be empty.");
    }
  }

}
