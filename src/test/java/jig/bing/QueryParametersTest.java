package jig.bing;

import jig.constants.AdultOption;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyler on 12/25/15.
 */
public class QueryParametersTest {

  QueryParameters searchTermOnly = new QueryParameters("Test");
  QueryParameters searchTermWithSpaces = new QueryParameters("Test Spaces");
  QueryParameters adultOptionOnly = new QueryParameters(AdultOption.STRICT);
  QueryParameters queryOptionsOnly = new QueryParameters(50, AdultOption.STRICT);
  QueryParameters noQueryOptions = new QueryParameters();

  @Test
  public void testSearchTermOnly() {
    String expected = "&Query=%27Test%27";
    assertEquals(expected, searchTermOnly.getEncodedSearchTerm());
  }

  @Test
  public void testSearchTermWithSpaces() {
    String expected = "&Query=%27Test+Spaces%27";
    assertEquals(expected, searchTermOnly.getEncodedSearchTerm());
  }



}