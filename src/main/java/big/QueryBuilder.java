package big;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class QueryBuilder {
	
	private final String ROOT_URL = "https://api.datamarket.azure.com/Bing/Search/Image";
	private final String QUERY_MARKET = "&Market=%27en-US%27";
	private final String QUERY_RETURN_TYPE = "&$format=JSON";
	private String queryTerm;
	private String queryFilter;
	private String queryResultAmount;
	private String queryOffset;
	private int remainingImages;
	
	public QueryBuilder(String queryChoice, int imageCount, String queryFilter) {
		this.queryTerm = queryChoice;
		this.queryFilter = queryFilter;
		if(imageCount > 50) {
			this.queryResultAmount = "50";
			this.remainingImages = imageCount - 50;
		}
		else this.queryResultAmount = String.valueOf(imageCount);	// Image download count
		this.queryOffset = String.valueOf(0);
	}

	public void encodeParameters() {
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
}
