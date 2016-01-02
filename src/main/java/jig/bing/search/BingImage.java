package jig.bing.search;

import java.awt.image.BufferedImage;
import jig.bing.image.ImageResult;

/**
 * Created by tyler on 1/2/16.
 */
public class BingImage implements Runnable{

  private ImageResult imageToDownload;
  private BufferedImage image;

  public BingImage(ImageResult imageToDownload) {
    this.imageToDownload = imageToDownload;
  }

  @Override
  public void run() {

  }

  public BufferedImage getImage() {
    return this.image;
  }
}
