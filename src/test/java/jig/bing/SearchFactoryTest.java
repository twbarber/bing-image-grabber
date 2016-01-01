package jig.bing;

import jig.bing.enums.AdultOption;
import jig.bing.search.ImageRequest;
import jig.bing.search.ImageRequestFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchFactoryTest {

  private final String API_BASE = "https://api.datamarket.azure.com/Bing/ImageRequest/Image?";
  private ImageRequestFactory testRequestFactory = new ImageRequestFactory();

  @Test
  public void testRandomQuery() {
    ImageRequest testRequest = testRequestFactory.createRequest();
    assertTrue(testRequest.getRequestUrl().contains("top=50"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

	@Test
	public void testTerm() {
    String expected = API_BASE + "Query=%27Test%27&Adult=%27Strict%27&$top=50&$format=JSON";
    ImageRequest testRequest = testRequestFactory.createRequest("Test");
    assertEquals(expected, testRequest.getRequestUrl());
	}

  @Test
  public void testNumber() {
    ImageRequest testRequest = testRequestFactory.createRequest(100);
    assertTrue(testRequest.getRequestUrl().contains("top=100"));
  }

  @Test
  public void testAdult() {
    ImageRequest testRequest = testRequestFactory.createRequest(AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

  @Test
  public void testTermAndAdult() {
    ImageRequest testRequest = testRequestFactory.createRequest("Test", AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrl().contains("%27Test%27"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

  @Test
  public void testTermAndNumber() {
    String expected = API_BASE + "Query=%27Test%27&Adult=%27Strict%27&$top=50&$format=JSON";
    ImageRequest testRequest = testRequestFactory.createRequest("Test", 50);
    assertEquals(expected, testRequest.getRequestUrl());
  }

  @Test
  public void testAdultAndNumber() {
    ImageRequest testRequest = testRequestFactory.createRequest(50, AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrl().contains("top=50"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));  }

  @Test
  public void testTermNumberAndAdult() {
    ImageRequest testRequest = testRequestFactory.createRequest("Test", 50, AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrl().contains("%27Test%27"));
    assertTrue(testRequest.getRequestUrl().contains("top=50"));
    assertTrue(testRequest.getRequestUrl().contains("%27Strict%27"));
  }

  @Test
  public void testQueryWithSpaces() {
    String expected = API_BASE + "Query=%27Test+One%27&Adult=%27Strict%27&$top=50&$format=JSON";
    ImageRequest testRequest = testRequestFactory.createRequest("Test One", 50, AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrl());
  }

}


