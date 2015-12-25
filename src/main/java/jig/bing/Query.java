package jig.bing;

/**
 * Created by Tyler on 12/25/15.
 */
public class Query {

  private QueryParameters parameters;
  private String query;

  public Query(QueryParameters parameters, String query) {
    this.parameters = parameters;
    this.query = query;
  }

  public QueryParameters getParameters() {
    return parameters;
  }

  public String getQuery() {
    return query;
  }
}
