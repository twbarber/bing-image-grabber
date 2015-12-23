package big;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Collection;

/**
 * Created by Tyler on 12/23/15.
 */
public interface ImageFinder {

  public Collection<URL> getImageUrls(String searchTerm);

  public Collection<URL> getImageUrls(String searchTerm, int numberOfImages);

  public Collection<URL> getImageUrls(String searchTerm, int numberOfImages, AdultOption adultOption);

  public Collection<URL> getImageUrls(String searchTerm, int numberOfImages,
      AdultOption adultOption, Market market);
}
