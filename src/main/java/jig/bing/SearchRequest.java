package jig.bing;

import java.net.URL;

/**
 * Wrapper Object for Bing Image Request and it's Parameters.
 */
public class SearchRequest {

  private SearchRequestParameters parameters;
  private URL requestUrl;

  public SearchRequest(SearchRequestParameters parameters, URL requestUrl) {
    this.parameters = parameters;
    this.requestUrl = requestUrl;
  }

  public SearchRequestParameters getParameters() {
    return parameters;
  }

  public URL getRequestUrl() {
    return requestUrl;
  }

  public String getRequestUrlAsString() {
    return this.requestUrl.toString();
  }
}
