package jig.bing;

import jig.constants.AdultOption;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyler on 12/25/15.
 */
public class ImageRequestParametersTest {

  ImageRequestParameters searchTermOnly = new ImageRequestParameters("Test");
  ImageRequestParameters searchTermWithSpaces = new ImageRequestParameters("Test Spaces");
  ImageRequestParameters adultOptionOnly = new ImageRequestParameters(AdultOption.STRICT);
  ImageRequestParameters queryOptionsOnly = new ImageRequestParameters(50, AdultOption.STRICT);
  ImageRequestParameters noQueryOptions = new ImageRequestParameters();

  @Test
  public void testSearchTermOnly() {
    String expected = "&ImageRequest=%27Test%27";
    assertEquals(expected, searchTermOnly.getEncodedSearchTerm());
  }

  @Test
  public void testSearchTermWithSpaces() {
    String expected = "&ImageRequest=%27Test+Spaces%27";
    assertEquals(expected, searchTermOnly.getEncodedSearchTerm());
  }



}