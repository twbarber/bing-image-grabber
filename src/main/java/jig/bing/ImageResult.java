package jig.bing;

import jig.util.StringUtils;

import java.net.URL;

/**
 * Stores the POJO version of an Image Result from the Bing Search API.
 */
public class ImageResult {

  private String id;
  private String title;
  private URL mediaUrl;
  private URL sourceUrl;
  private URL displayUrl;
  private int width;
  private int height;
  private int fileSize;
  private String contentType;
  private String Thumbnail;

  public ImageResult(String id, String title, String mediaUrl, String sourceUrl, String displayUrl, int width,
      int height, int fileSize, String contentType, String thumbnail) {
    this.id = id;
    this.title = title;
    this.mediaUrl = StringUtils.convertToUrl(mediaUrl);
    this.sourceUrl = StringUtils.convertToUrl(sourceUrl);
    this.displayUrl = StringUtils.convertToUrl(displayUrl);
    this.width = width;
    this.height = height;
    this.fileSize = fileSize;
    this.contentType = contentType;
    Thumbnail = thumbnail;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public URL getMediaUrl() {
    return mediaUrl;
  }

  public URL getSourceUrl() {
    return sourceUrl;
  }

  public URL getDisplayUrl() {
    return displayUrl;
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

  public String getContentType() {
    return contentType;
  }

  public String getThumbnail() {
    return Thumbnail;
  }

}
