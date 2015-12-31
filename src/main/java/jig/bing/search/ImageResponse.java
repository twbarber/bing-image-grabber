package jig.bing.search;

import java.util.Collection;
import jig.bing.image.ImageResult;

/**
 * Created by tyler on 12/31/15.
 */
public class ImageResponse {

  private Collection<ImageResult> results;

  public ImageResponse(Collection<ImageResult> results) {
    this.results = results;
  }

  public Collection<ImageResult> getResults() {
    return results;
  }

  public int total() {
    return this.results.size();
  }

}
