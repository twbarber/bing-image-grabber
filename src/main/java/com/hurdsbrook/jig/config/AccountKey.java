package com.hurdsbrook.jig.config;

import org.apache.commons.codec.binary.Base64;

/**
 * Used for storing encrypted Bing Account Key.
 *
 * Stores the passed in account key using Base64 encoding for use
 * with the Bing Search API.
 */
public class AccountKey {

  private final byte[] encondedKey;

  public AccountKey(String accountKey) {
    this.encondedKey = Base64.encodeBase64(accountKey.getBytes());
  }

  public byte[] getEncondedKey() {
    return this.encondedKey;
  }

}
