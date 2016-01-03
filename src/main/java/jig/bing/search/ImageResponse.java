package jig.bing.search;

import java.util.ArrayList;
import java.util.Collection;
import jig.bing.image.ImageResult;

/**
 * Container object for full set of ImageResults.
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

  public Collection<String> getImageUrls() {
    ArrayList<String> urls = new ArrayList<>();
    for (ImageResult result : getResults()) {
      urls.add(result.getMediaUrl());
    }
    return urls;
  }

}
