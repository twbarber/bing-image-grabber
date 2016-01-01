package jig.bing;

import jig.bing.enums.AdultOption;
import jig.bing.enums.Market;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageRequestBuilderTest {

  private final String API_BASE = "https://api.datamarket.azure.com/Bing/ImageRequest/Image?";

  @Test
  public void testRandomQuery() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains("top=50"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

	@Test
	public void testTerm() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setSearchTerm("Test");
    ImageRequest testRequest = testBuilder.buildRequest();
    String expected = API_BASE + "Query=%27Test%27&Adult=%27Strict%27&$top=50&$format=JSON";
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
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

  @Test
  public void testMarket() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setMarket(Market.EN_US);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains("%27en-US%27"));
  }

  @Test
  public void testTermAndAdult() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setAdultOption(AdultOption.STRICT);
    testBuilder.setSearchTerm("Test");
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains("%27Test%27"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

  @Test
  public void testTermAndNumber() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setNumberOfImages(50);
    testBuilder.setSearchTerm("Test");
    ImageRequest testRequest = testBuilder.buildRequest();
    String expected = API_BASE + "Query=%27Test%27&Adult=%27Strict%27&$top=50&$format=JSON";
    assertEquals(expected, testRequest.getRequestUrl());
  }

  @Test
  public void testAdultAndNumber() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setNumberOfImages(50);
    testBuilder.setAdultOption(AdultOption.STRICT);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains("top=50"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));  }

  @Test
  public void testTermNumberAndAdult() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setSearchTerm("Test");
    testBuilder.setNumberOfImages(50);
    testBuilder.setAdultOption(AdultOption.STRICT);
    ImageRequest testRequest = testBuilder.buildRequest();
    assertTrue(testRequest.getRequestUrl().contains("%27Test%27"));
    assertTrue(testRequest.getRequestUrl().contains("top=50"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

  @Test
  public void testQueryWithSpaces() {
    ImageRequestBuilder testBuilder = new ImageRequestBuilder();
    testBuilder.setSearchTerm("Test One");
    testBuilder.setNumberOfImages(50);
    testBuilder.setAdultOption(AdultOption.STRICT);
    ImageRequest testRequest = testBuilder.buildRequest();
    String expected = API_BASE + "Query=%27Test+One%27&Adult=%27Strict%27&$top=50&$format=JSON";
    assertEquals(expected, testRequest.getRequestUrl());
  }

}
