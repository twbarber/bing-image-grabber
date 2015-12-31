package jig.bing;

import jig.bing.enums.AdultOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchFactoryTest {

  SearchRequestFactory testRequestFactory = new SearchRequestFactory();

  @Test
  public void testRandomQuery() {
    SearchRequest testRequest = testRequestFactory.createRequest();
    assertTrue(testRequest.getRequestUrlAsString().contains("top=50"));
    assertTrue(testRequest.getRequestUrlAsString().contains("%27Strict%27"));
  }

	@Test
	public void testTerm() {
    String expected = "https://api.datamarket.azure.com/Bing/SearchRequest/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    SearchRequest testRequest = testRequestFactory.createRequest("Test");
    assertEquals(expected, testRequest.getRequestUrlAsString());
	}

  @Test
  public void testNumber() {
    SearchRequest testRequest = testRequestFactory.createRequest(100);
    assertTrue(testRequest.getRequestUrlAsString().contains("top=100"));
  }

  @Test
  public void testAdult() {
    SearchRequest testRequest = testRequestFactory.createRequest(AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrlAsString().contains("%27Strict%27"));
  }

  @Test
  public void testTermAndAdult() {
    SearchRequest testRequest = testRequestFactory.createRequest("Test", AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrlAsString().contains("%27Test%27"));
    assertTrue(testRequest.getRequestUrlAsString().contains("%27Strict%27"));
  }

  @Test
  public void testTermAndNumber() {
    String expected = "https://api.datamarket.azure.com/Bing/SearchRequest/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    SearchRequest testRequest = testRequestFactory.createRequest("Test", 50);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testAdultAndNumber() {
    SearchRequest testRequest = testRequestFactory.createRequest(50, AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrlAsString().contains("top=50"));
    assertTrue(testRequest.getRequestUrlAsString().contains("%27Strict%27"));  }

  @Test
  public void testTermNumberAndAdult() {
    SearchRequest testRequest = testRequestFactory.createRequest("Test", 50, AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrlAsString().contains("%27Test%27"));
    assertTrue(testRequest.getRequestUrlAsString().contains("top=50"));
    assertTrue(testRequest.getRequestUrlAsString().contains("%27Strict%27"));
  }

  @Test
  public void testQueryWithSpaces() {
    String expected = "https://api.datamarket.azure.com/Bing/SearchRequest/Image?$format=JSON" +
        "&Query=%27Test+One%27&Adult=%27Strict%27&$top=50";
    SearchRequest testRequest = testRequestFactory.createRequest("Test One", 50, AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

}


