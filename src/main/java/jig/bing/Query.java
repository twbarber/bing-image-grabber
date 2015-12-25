package jig.bing;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tyler on 12/25/15.
 */
public class Query {

  private QueryParameters parameters;
  private URL queryUrl;

  public Query(QueryParameters parameters) {
    this.parameters = parameters;
    buildQueryUrl(parameters);
  }

  private void buildQueryUrl(QueryParameters parameters) {
    try {
      this.queryUrl = new URL(QueryBuilder.generateQuery(parameters));
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Bad parameters for search: " + parameters);
    }
  }

  public QueryParameters getParameters() {
    return parameters;
  }

  public URL getQueryUrl() {
    return queryUrl;
  }
}
