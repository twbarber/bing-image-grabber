package jig.bing;

import org.apache.log4j.Logger;

/**
 * Used to build search queries for the Java Image Grabber.
 */
public class QueryFactory {

  private static Logger logger = Logger.getLogger(QueryFactory.class);
  private static final String ROOT_URL = "https://api.datamarket.azure.com/Bing/Search/Image?$format=JSON";

	public static String generateQuery(QueryParameters parameters) {
    logger.info("Building query with parameters: " + parameters.toString());
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append(ROOT_URL);
    queryBuilder.append(parameters.getEncodedSearchTerm());
    queryBuilder.append(parameters.getEncodedAdultOption());
    queryBuilder.append(parameters.getEncodedNumberOfImages());
    return queryBuilder.toString();
	}

}
