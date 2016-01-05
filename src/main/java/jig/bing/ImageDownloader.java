package jig.bing;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * Multi-threaded download service used to get Images gathered by
 * the BingService.
 *
 */
public class ImageDownloader {

  private Logger logger = Logger.getLogger(ImageDownloader.class);

  /**
   * Downloads images from a list of URLs
   * @param imageUrls URLs of images to download.
   * @return Resulting collection of images downloaded to memory.
   */
  public Collection<BufferedImage> downloadImages(Collection<String> imageUrls) {
    this.logger.info("Downloading " + imageUrls.size() + " images.");
    ExecutorService executor = Executors.newCachedThreadPool();
    List<DownloadTask> imageDownloadTasks = buildDownloadTasks(imageUrls);
    Collection<DownloadTask> images = Collections.synchronizedList(imageDownloadTasks);
    for (DownloadTask image : images) {
      executor.execute(image);
    }
    shutdownExecutor(executor);
    return extractDownloadedImages(imageDownloadTasks);
  }

  private List<DownloadTask> buildDownloadTasks(Collection<String> imagesToDownload) {
    List<DownloadTask> DownloadTasks = new ArrayList<>();
    for (String imageUrl : imagesToDownload) {
      DownloadTasks.add(new DownloadTask(imageUrl));
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

    private Logger logger = Logger.getLogger(DownloadTask.class);
    private String imageUrl;
    private BufferedImage image;
    private OkHttpClient client;

    public DownloadTask(String imageToDownload) {
      this.imageUrl = imageToDownload;
      this.client = new OkHttpClient();
    }

    @Override
    public void run() {
      this.logger.info("Downloading Image from: " + this.imageUrl);
      if (jig.util.StringUtils.isNotNullOrEmpty(this.imageUrl)) {
        downloadImage();
      } else {
        this.logger.info("Image URLs cannot be Null or Empty, skipping.");
      }
    }

    private void downloadImage() {
      try {
        Request downloadRequest = new Request.Builder()
            .url(this.imageUrl)
            .build();
        Response response = client.newCall(downloadRequest).execute();
        InputStream in = response.body().byteStream();
        this.image = ImageIO.read(in);
      } catch (IOException | IllegalArgumentException e) {
        this.logger.error(String.format("Couldn't download image from URL: \"%s\"", this.imageUrl));
      }
    }

    public BufferedImage getImage() {
      return this.image;
    }
  }

}
