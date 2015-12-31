package jig.bing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import jig.config.AccountKey;
import jig.config.Config;
import jig.image.ImageResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by tyler on 12/28/15.
 */
public class BingServiceTest {

  private Config config;
  private ImageRequestFactory requestFactory = new ImageRequestFactory();
  private BingService serviceUnderTest;

  @Before
  public void setup() {
    Properties configProperties = loadConfigProperties();
    AccountKey accountKey = new AccountKey(configProperties.getProperty("account.key"));
    this.config = new Config(accountKey);
    this.serviceUnderTest = new BingService(config);
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

  @Test
  public void testSearch() throws Exception {
    ImageRequest request = requestFactory.createRequest("cat gif");
    Collection<ImageResult> results = this.serviceUnderTest.search(request);
    assertTrue(results.size() != 0);
  }

  @Test
  public void testDownloadImages() throws Exception {
    assertTrue(true);
  }

}