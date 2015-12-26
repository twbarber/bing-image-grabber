package jig.bing;

import jig.constants.AdultOption;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImageRequestFactoryTest {

  ImageRequestFactory testRequestFactory = new ImageRequestFactory();

	@Test
	public void testTerm() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test");
    assertEquals(expected, testRequest.getRequestUrlAsString());
	}

  @Test
  public void testNumber() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27" + Pattern.quote("/d+") + "%27&Adult=%27Strict%27&$top=49";
    ImageRequest testRequest = testRequestFactory.createRequest(49);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testAdult() {
    String expected = "\\bhttps://api.datamarket.azure.com/Bing/Search/Image?$format=JSON\\b" +
        "\\b&Query=%27\\b" + "\\d{7}" + "\\b%27&Adult=%27Strict%27&$top=50\\b";
    ImageRequest testRequest = testRequestFactory.createRequest(AdultOption.STRICT);
    assertTrue(testRequest.getRequestUrlAsString().matches(expected)+);
  }

  @Test
  public void testTermAndAdult() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27" + "\\d{7}" + "%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test", AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testTermAndNumber() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test", 50);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testAdultAndNumber() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest(50, AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testTermNumberAndAdult() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test", 50, AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

  @Test
  public void testQueryWithSpaces() {
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test+One%27&Adult=%27Strict%27&$top=50";
    ImageRequest testRequest = testRequestFactory.createRequest("Test One", 50, AdultOption.STRICT);
    assertEquals(expected, testRequest.getRequestUrlAsString());
  }

}


