package jig.image;

/**
 * Created by tyler on 12/28/15.
 */
public class Thumbnail {

  private String mediaUrl;
  private int width;
  private int height;
  private int fileSize;

  public Thumbnail(String mediaUrl, int width, int height, int fileSize) {
    this.mediaUrl = mediaUrl;
    this.width = width;
    this.height = height;
    this.fileSize = fileSize;
  }

  public String getContentType() {
    return contentType;
  }

  public String getMediaUrl() {
    return mediaUrl;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getFileSize() {
    return fileSize;
  }

  private String contentType;


}
