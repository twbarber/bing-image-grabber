package jig.bing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import jig.bing.image.ImageResult;
import jig.bing.search.BingImage;
import org.apache.log4j.Logger;

/**
 * Used to download Images gathered by the BingService.
 *
 */
public class ImageDownloader {

  private Logger logger = Logger.getLogger(ImageDownloader.class);

  public Collection<BufferedImage> downloadImages(Collection<ImageResult> imageSearchResults) {
    ExecutorService executor = Executors.newCachedThreadPool();
    List<BingImage> imagesToDownload = convertToBingImages(imageSearchResults);
    Collection<BingImage> images = Collections.synchronizedList(imagesToDownload);
    for (BingImage image : images) {
      executor.execute(image);
    }
    executor.shutdown();
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
      this.logger.error("There was problem downloading all requested images.");
    }
    return extractDownloadedImages(imagesToDownload);
  }

  private List<BingImage> convertToBingImages(Collection<ImageResult> imagesToDownload) {
    List<BingImage> bingImages = new ArrayList<>();
    for (ImageResult imageResult : imagesToDownload) {
      bingImages.add(new BingImage(imageResult));
    }
    return bingImages;
  }

  private Collection<BufferedImage> extractDownloadedImages(Collection<BingImage> bingImages) {
    List<BufferedImage> images = new ArrayList<>();
    for (BingImage imageResult : bingImages) {
      if (imageResult.getImage() != null) {
        images.add(imageResult.getImage());
      }
    }
    return images;
  }

}