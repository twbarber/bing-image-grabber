package jig.bing;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import jig.bing.image.ImageResult;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;

/**
 * Used to download Images gathered by the BingService.
 *
 */
public class ImageDownloader {

  private Logger logger = Logger.getLogger(ImageDownloader.class);

  public Collection<BufferedImage> downloadImages(Collection<String> imageSearchResults) {
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

  private List<DownloadTask> convertToDownloadTasks(Collection<String> imagesToDownload) {
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
