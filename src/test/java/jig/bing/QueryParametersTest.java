package jig.bing;

import jig.constants.AdultOption;

import static org.junit.Assert.*;

/**
 * Created by Tyler on 12/25/15.
 */
public class QueryParametersTest {

  QueryParameters searchTermOnly = new QueryParameters("Test");
  QueryParameters searchTermAndCount = new QueryParameters("Test", AdultOption.STRICT);
  QueryParameters searchTermAndAdult = new QueryParameters("Test", 50);
  QueryParameters allQueryOptions = new QueryParameters("Test", 50, AdultOption.STRICT);
  QueryParameters noQueryOptions = new QueryParameters();



}