package jig.bing;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to download Images gathered by the BingService.
 */
public class ImageDownloader {

  private Logger logger = Logger.getLogger(ImageDownloader.class);
  public Collection<BufferedImage> images = new ArrayList<>();

	public void saveImages(Collection<ImageResult> imagesToDownload) {
      int imageIndex = 0;
      for (ImageResult image : imagesToDownload) {
        try {
          String fileName = "/img" + imageIndex;
          File img = new File(fileName);

          OutputStream os = new FileOutputStream(img.toString());

          byte[] b = new byte[2048];
          int length;

          os.close();

          imageIndex++;
        } catch (IOException e) {
          this.logger.error("Error saving images.");
        }

      }

    }

}