package jig.bing.search;

import java.net.URL;

/**
 * Wrapper Object for Bing Image Request and it's Parameters.
 */
public class ImageRequest {

  private ImageRequestParameters parameters;
  private URL requestUrl;

  public ImageRequest(ImageRequestParameters parameters, URL requestUrl) {
    this.parameters = parameters;
    this.requestUrl = requestUrl;
  }

  public ImageRequestParameters getParameters() {
    return parameters;
  }

  public URL getRequestUrl() {
    return requestUrl;
  }

  public String getRequestUrlAsString() {
    return this.requestUrl.toString();
  }
}
