package com.hurdsbrook.jig.config;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Tyler on 12/24/15.
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
