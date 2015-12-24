package jig;

import config.Config;

import java.io.IOException;

/**
 * Created by Tyler on 12/23/15.
 */
public class ImageGrabber {

  private final Config config;

  public ImageGrabber(Config config) {
    this.config = config;
  }

  private void authenticate() {
    KeyHandler keyHandler = new KeyHandler();
    try {
      String userKey = keyHandler.getExistingKey();
      while (keyHandler.isValidKey(userKey)) {
        userKey = this.config.getApiKey();
        if (keyHandler.isValidKey(userKey)) {
          keyHandler.writeKey(userKey);
          return;
        }
      }
    } catch (IOException e) {
      System.err.println("There was an error authenticating your key.");
    }
  }

}
