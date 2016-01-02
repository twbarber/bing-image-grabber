package jig.bing;

import jig.bing.enums.AdultOption;
import jig.bing.enums.Market;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageRequestBuilderTest {

  private final String API_BASE = "https://api.datamarket.azure.com/Bing/Search/Image?";
  private final String TEST_ADULT = "%27Strict%27";
  private final String TEST_NUMBER = "top=50";
  private final String TEST_MARKET = "%27en-US%27";
  private final String TEST_SEARCH = "%27Test%27";

  @Test
  public void testRandomQuery() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains(TEST_NUMBER));
    assertTrue(testRequest.getRequestUrl().contains(TEST_ADULT));
  }

	@Test
	public void testTerm() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setSearchTerm("Test");
    ImageRequest testRequest = testBuilder.buildRequest();
    String expected = API_BASE + "Query=%27Test%27&Adult=%27Strict%27&$top=50&$format=json";
    assertEquals(expected, testRequest.getRequestUrl());
	}

  @Test
  public void testNumber() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setNumberOfImages(100);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains("top=100"));
  }

  @Test
  public void testAdult() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setAdultOption(AdultOption.STRICT);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains(TEST_ADULT));
  }

  @Test
  public void testMarket() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setMarket(Market.EN_US);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains(TEST_MARKET));
  }

  @Test
  public void testTermAndAdult() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setAdultOption(AdultOption.STRICT);
    testBuilder.setSearchTerm("Test");
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains(TEST_SEARCH));
    assertTrue(testRequest.getRequestUrl().contains(TEST_ADULT));
  }

  @Test
  public void testTermAndNumber() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setNumberOfImages(50);
    testBuilder.setSearchTerm("Test");
    ImageRequest testRequest = testBuilder.buildRequest();
    String expected = API_BASE + "Query=%27Test%27&Adult=%27Strict%27&$top=50&$format=json";
    assertEquals(expected, testRequest.getRequestUrl());
  }

  @Test
  public void testAdultAndNumber() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setNumberOfImages(50);
    testBuilder.setAdultOption(AdultOption.STRICT);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains(TEST_NUMBER));
    assertTrue(testRequest.getRequestUrl().contains(TEST_ADULT));  }

  @Test
  public void testTermNumberAndAdult() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setSearchTerm("Test");
    testBuilder.setNumberOfImages(50);
    testBuilder.setAdultOption(AdultOption.STRICT);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains(TEST_SEARCH));
    assertTrue(testRequest.getRequestUrl().contains(TEST_NUMBER));
    assertTrue(testRequest.getRequestUrl().contains(TEST_ADULT));
  }

  @Test
  public void testQueryWithSpaces() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setSearchTerm("Test One");
    testBuilder.setNumberOfImages(50);
    testBuilder.setAdultOption(AdultOption.STRICT);
    ImageRequest testRequest = testBuilder.buildRequest();
    String expected = API_BASE + "Query=%27Test+One%27&Adult=%27Strict%27&$top=50&$format=json";
    assertEquals(expected, testRequest.getRequestUrl());
  }

}
