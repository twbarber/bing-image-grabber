package jig.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import jig.bing.BingService;
import jig.bing.ImageDownloader;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestBuilder;
import jig.config.AccountKey;
import jig.config.Config;
import org.apache.log4j.Logger;

/**
 * Main class to be used in jar.
 *
 * Takes in ImageRequest term and download directory as args. First run will ask user to properly
 * configure the JIG Config file with Account Key and default download directory if it isn't already
 * configured.
 *
 * Preceding runs will download images to the default directory or directory given as argument if
 * the Config's account key is valid.
 */
public class Jig {

  private Logger logger = Logger.getLogger(Jig.class);
  private final String INVALID_ARGUMENTS_ERROR =
      "Usage: java -jar path/to/jar <search-term> <download-directory>";

  public static void main(String[] args) {
    if (args.length == 2) {
      Jig jig = new Jig();
      jig.run(args);
    }
  }

  private void run(String[] args) {
    // Load JIG Config
    Config config = getConfig();
    BingService bing = new BingService(config);

    // Build Search Request and Execute
    ImageRequestBuilder builder = new ImageRequestBuilder()
        .setSearchTerm("cat")
        .setNumberOfImages(5);
    ImageRequest request = builder.buildRequest();

    // Execute Search and Download Resulting Images
    Collection<String> imageUrls = bing.search(request).getImageUrls();
    ImageDownloader downloader = new ImageDownloader();
    Collection<BufferedImage> images = new ArrayList<>();
    images.addAll(downloader.download(imageUrls));

    // Draw all Downloaded Images
    for (BufferedImage image : images) {
      drawImage(image);
    }
  }

  private Config getConfig() {
    Properties configProperties = loadConfigProperties();
    AccountKey accountKey = new AccountKey(configProperties.getProperty("account.key"));
    return new Config(accountKey);
  }

  private Properties loadConfigProperties() {
    InputStream configStream =
        ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
    Properties properties = new Properties();
    try {
      properties.load(configStream);
    } catch (IOException e) {
      System.err.print("Couldn't load config.properties");
    }
    return properties;
  }

  private void drawImage(BufferedImage image) {

  }

  private boolean isValidSearchTerm(String searchTerm) {
    return true;
  }

  private boolean isvalidDownloadDirectory(String downloadDiretory) {
    return true;
  }
}
