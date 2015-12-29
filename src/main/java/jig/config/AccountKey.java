package jig.config;

/**
 * Used for storing encrypted Bing Account Key.
 *
 * Stores the passed in account key using Base64 encoding for use
 * with the Bing Search API.
 */
public class AccountKey {

  private final String encodedKey;

  public AccountKey(String accountKey) {
    this.encodedKey = accountKey;
  }

  public String getEncondedKey() {
    return encodedKey;
  }

}
