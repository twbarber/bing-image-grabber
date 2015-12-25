package jig.bing;

import jig.constants.AdultOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

	@Test
	public void testBasicQuery() {
		ImageRequestParameters basicSearchParamters = new ImageRequestParameters("Test", 50, AdultOption.STRICT);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
	}

  @Test
  public void testExplicitImageNumber() {
    ImageRequestParameters basicSearchParamters = new ImageRequestParameters("Test", 50);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }

  @Test
  public void testExplicitAdultOption() {
    ImageRequestParameters basicSearchParamters = new ImageRequestParameters("Test", 50, AdultOption.STRICT);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }

  @Test
  public void testQueryWithSpaces() {
    ImageRequestParameters basicSearchParamters = new ImageRequestParameters("Test One", 50, AdultOption.STRICT);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test+One%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }

  @Test
  public void testQueryOnly() {
    ImageRequestParameters basicSearchParamters = new ImageRequestParameters("Test");
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&ImageRequest=%27Test+%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }


}


