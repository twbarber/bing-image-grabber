package jig.bing;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to download Images gathered by the BingService.
 */
public class ImageDownloader {

  private Logger logger = Logger.getLogger(ImageDownloader.class);
  public Collection<BufferedImage> images = new ArrayList<>();

	public void saveImages(Collection<ImageResult> imagesToDownload, Path directoryToWriteTo) {
      int imageIndex = 0;
      for (ImageResult image : imagesToDownload) {
        try {
          String fileName = "/img" + imageIndex;
          File img = new File(fileName);
          InputStream is = image.getSourceUrl().openStream();

          OutputStream os = new FileOutputStream(img.toString());

          byte[] b = new byte[2048];
          int length;

          while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
          }

          is.close();
          os.close();

          imageIndex++;
        } catch (IOException e) {
        }

      }

    }

}