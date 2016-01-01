package jig.main;

import jig.bing.BingService;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestBuilder;
import jig.bing.search.ImageResponse;
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

  public void run(String[] args) {
    if (args.length == 2) {
      String searchTerm = args[0];
      String downloadDirectory = args[1];
      Config config = new Config(new AccountKey(""));
      ImageRequestBuilder builder = new ImageRequestBuilder();
      ImageRequest request = builder.buildRequest();
      BingService bing = new BingService(config);
      try {
        ImageResponse response = bing.search(request);
      } catch (Exception e) {

      }
    } else {
      this.logger.error(INVALID_ARGUMENTS_ERROR);
    }
  }

  private boolean isValidSearchTerm(String searchTerm) {
    return true;
  }

  private boolean isvalidDownloadDirectory(String downloadDiretory) {
    return true;
  }
}
