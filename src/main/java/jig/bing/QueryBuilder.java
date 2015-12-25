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

  private Logger logger = Logger.getLogger(QueryBuilder.class);
  private final String ROOT_URL = "https://api.datamarket.azure.com/Bing/Search/Image";
	private final String QUERY_RETURN_TYPE = "&$format=JSON";
	private String queryOffset;
	private int remainingImages;

  public String

	public void encodeParameters(QueryParameters queryParameters) {
		this.queryTerm = "?Query=%27" + this.queryTerm + "%27";
		this.queryFilter = "&Adult=%27" + this.queryFilter + "%27";
		this.queryResultAmount = "&$top=" + this.queryResultAmount; 
		this.queryOffset = "&$skip=" + this.queryOffset;
	}

	public Collection<URL> generateQuerys() throws MalformedURLException {
		ArrayList<URL> queryURLs = new ArrayList<URL>();
		for(int i = 0; i < this.remainingImages; i++) {
			String queryString = ROOT_URL + this.queryTerm + this.queryFilter + QUERY_MARKET +
					this.queryResultAmount + this.queryOffset + QUERY_RETURN_TYPE;
			URL queryUrl = new URL(queryString);
			queryURLs.add(queryUrl);
			adjustRemainingImageCount();
		}
		return queryURLs;
	} 
	
	public void adjustRemainingImageCount() {
		if(this.remainingImages > 50) {
			this.remainingImages -= 50;
		}
		else {
			this.queryResultAmount = String.valueOf(this.remainingImages);
			this.remainingImages = 0;
		}
	}

	public String generateQueryTerm() {
		int generatedNum = 100000 + (int)(Math.random() * ((899999) + 1));
		return String.valueOf(generatedNum);
	}

  private Collection<URL> buildQuery(String queryTerm, int numberImages, String queryFilter) throws MalformedURLException {
    QueryBuilder queryBuilder = new QueryBuilder(queryTerm, numberImages, queryFilter);
    queryBuilder.encodeParameters();
    return queryBuilder.generateQuerys();
  }
}
