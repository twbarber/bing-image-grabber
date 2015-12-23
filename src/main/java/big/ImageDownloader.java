package big;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Collection;

/**
 * Created by Tyler on 12/23/15.
 */
public interface ImageDownloader {

  public Collection<BufferedImage> downloadImages(Collection<URL> imagesToDownload);

}

