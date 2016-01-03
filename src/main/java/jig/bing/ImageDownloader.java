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
import org.apache.log4j.Logger;

/**
 * Used to download Images gathered by the BingService.
 *
 */
public class ImageDownloader {

  private Logger logger = Logger.getLogger(ImageDownloader.class);

  public Collection<BufferedImage> downloadImages(Collection<ImageResult> imageSearchResults) {
    this.logger.info("Downloading " + imageSearchResults.size() + " images.");
    ExecutorService executor = Executors.newCachedThreadPool();
    List<DownloadTask> imagesToDownload = convertToDownloadTasks(imageSearchResults);
    Collection<DownloadTask> images = Collections.synchronizedList(imagesToDownload);
    for (DownloadTask image : images) {
      executor.execute(image);
    }
    shutdownExecutor(executor);
    return extractDownloadedImages(imagesToDownload);
  }

  private List<DownloadTask> convertToDownloadTasks(Collection<ImageResult> imagesToDownload) {
    List<DownloadTask> DownloadTasks = new ArrayList<>();
    for (ImageResult imageResult : imagesToDownload) {
      DownloadTasks.add(new DownloadTask(imageResult));
    }
    return DownloadTasks;
  }

  private Collection<BufferedImage> extractDownloadedImages(Collection<DownloadTask> DownloadTasks) {
    List<BufferedImage> images = new ArrayList<>();
    for (DownloadTask imageResult : DownloadTasks) {
      if (imageResult.getImage() != null) {
        images.add(imageResult.getImage());
      }
    }
    return images;
  }

  private void shutdownExecutor(ExecutorService executorService) {
    executorService.shutdown();
    try {
      executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
      this.logger.error("There was problem downloading all requested images.");
    }
  }

  private class DownloadTask implements Runnable{

    private ImageResult imageToDownload;
    private BufferedImage image;

    public DownloadTask(ImageResult imageToDownload) {
      this.imageToDownload = imageToDownload;
    }

    @Override
    public void run() {

    }

    public BufferedImage getImage() {
      return this.image;
    }
  }

}
