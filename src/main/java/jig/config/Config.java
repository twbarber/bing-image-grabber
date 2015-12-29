package jig.config;

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
  private final AccountKey accountKey;

  public Config(AccountKey accountKey) {
    this.accountKey = accountKey;
    loadDefaults();
  }

  private String loadAccountKey(File file) {
    return "";
  }

  private void loadDefaults() {

  }

  public AccountKey getAccountKey() {
    return this.accountKey;
  }

}