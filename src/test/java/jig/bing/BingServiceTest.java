package jig.bing;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestBuilder;
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
public class BingServiceTest {

  private Config config;
  private BingService serviceUnderTest;

  @Before
  public void setup() {
    Properties configProperties = loadConfigProperties();
    String key = loadAccountKey(configProperties);
    AccountKey accountKey = new AccountKey(key);
    this.config = new Config(accountKey);
    this.serviceUnderTest = new BingService(config);
  }

  private String loadAccountKey(Properties configProperties) {
    if (configProperties.containsKey("account.key")) {
      return configProperties.getProperty("account.key");
    } else {
      return System.getProperty("account.key");
    }
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
    ImageRequestBuilder builder = new ImageRequestBuilder();
    ImageRequest request = builder.buildRequest();
    ImageResponse response = this.serviceUnderTest.search(request);
    assertTrue(response.getResults().size() != 0);
  }

  @Test
  public void testDownloadImages() throws Exception {
    assertTrue(true);
  }

}
