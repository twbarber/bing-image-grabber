package jig.bing;

import jig.config.Config;
import jig.config.KeyHandler;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Main interface for using the Java Image Grabber library.
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

  public Collection<BufferedImage> downloadImages(Collection<URL> imagesToDownload) {
    ImageDownloader imageDownloader = new ImageDownloader();
    return imageDownloader.downloadImages(imagesToDownload);
  }

  public Collection<URL> findImages(QueryParameters queryParameters) {
    ImageFinder imageFinder = new ImageFinder();
    return new ArrayList<>();
  }

}
