package jig.bing;

import jig.constants.AdultOption;
import jig.constants.Market;
import org.apache.log4j.Logger;
import sun.jvm.hotspot.oops.Mark;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class QueryBuilder {

  private static Logger logger = Logger.getLogger(QueryBuilder.class);
  private final String ROOT_URL = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON";

	public static String generateQuery(QueryParameters parameters) {
    logger.info("Building query with parameters: " + parameters.toString());
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append(ROOT_URL);
    queryBuilder.append(parameters.getEncodedSearchTerm());
    queryBuilder.append(parameters.getEncodedAdultOption());
    queryBuilder.append(parameters.getEncodedMarket());
    queryBuilder.append(parameters.getEncodedNumberOfImages());
    return queryBuilder.toString();
	}

}
