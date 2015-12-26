package jig.bing;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to download Images gathered by the ImageFinder.
 */
public class ImageDownloader {

	private Logger logger = Logger.getLogger(ImageDownloader.class);

	public Collection<BufferedImage> downloadImages(Collection<URL> imagesToDownload) {

    int imageCount = 0;
    System.out.print("\nDownloading Images");
    long startTime = System.nanoTime();
    int imageIndex = 0;

    for(URL imageURL : imagesToDownload) {
      System.out.print(".");
      try {
        String fileName = this.queryDirectory + "/img" + imageIndex;
        File img = new File(fileName);
        InputStream is = imageURL.openStream();

        OutputStream os = new FileOutputStream(img.toString());

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
          os.write(b, 0, length);
        }

        is.close();
        os.close();

        imageCount++;
      } catch (IOException e) {
      }

    }
    long endTime = System.nanoTime();
    long duration = endTime - startTime;
    double seconds = duration / 1000000000.0; // Converts time to download all images to seconds

    makeLog(seconds);

    System.out.printf("\n\nSaved %d images in %.2f seconds.", imageCount, seconds);

    return downloadedImages;
	}

  private void saveImages() {

  }


}