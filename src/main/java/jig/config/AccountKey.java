package jig.config;

/**
 * Used for storing encrypted Bing Account Key.
 *
 * Stores the passed in account key.
 */
public class AccountKey {

  private final String accountKey;

  public AccountKey(String accountKey) {
    this.accountKey = accountKey;
  }

  public String getAccountKey() {
    return accountKey;
  }

}
