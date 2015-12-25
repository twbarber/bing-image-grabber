package jig.bing;

import jig.constants.AdultOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageRequestFactoryTest {

  ImageRequestFactory testRequestFactory = new ImageRequestFactory();

	@Test
	public void testBasicQuery() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test");
    assertEquals(expected, testRequest.getRequestUrlAsString());
	}

  @Test
  public void testExplicitImageNumber() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test", 50);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testExplicitAdultOption() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test", 50, AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testQueryWithSpaces() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test+One%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test One", 50, AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testQueryOnly() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test+%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test");
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }


}


