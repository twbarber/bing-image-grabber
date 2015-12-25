package jig.bing;

import jig.constants.AdultOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

	@Test
	public void testBasicQuery() {
		QueryParameters basicSearchParamters = new QueryParameters("Test", 50, AdultOption.STRICT);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
	}

  @Test
  public void testExplicitImageNumber() {
    QueryParameters basicSearchParamters = new QueryParameters("Test", 50);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }

  @Test
  public void testExplicitAdultOption() {
    QueryParameters basicSearchParamters = new QueryParameters("Test", 50, AdultOption.STRICT);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }

  @Test
  public void testQueryWithSpaces() {
    QueryParameters basicSearchParamters = new QueryParameters("Test One", 50, AdultOption.STRICT);
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test+One%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }

  @Test
  public void testQueryOnly() {
    QueryParameters basicSearchParamters = new QueryParameters("Test");
    String expected = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON" +
        "&Query=%27Test+%27&Adult=%27Strict%27&$top=50";
    assertEquals(expected, QueryBuilder.generateQuery(basicSearchParamters));
  }


}


