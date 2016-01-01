package jig.bing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestFactory;
import jig.bing.search.ImageResponse;
import jig.config.AccountKey;
import jig.config.Config;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Test class for the BingService Class.
 */
@Ignore
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
    ImageResponse response = this.serviceUnderTest.search(request);
    assertTrue(response.getResults().size() != 0);
  }

  @Test
  public void testDownloadImages() throws Exception {
    assertTrue(true);
  }

}