package jig.bing;

/**
 * Stores the POJO version of an Image Result from the Bing Search API.
 */
public class ImageResult {

  private String id;
  private String title;
  private String mediaUrl;
  private String sourceUrl;
  private String displayUrl;
  private int width;
  private int height;
  private int fileSize;
  private String contentType;
  private String Thumbnail;

  public ImageResult(String id, String title, String mediaUrl, String sourceUrl, String displayUrl, int width,
      int height, int fileSize, String contentType, String thumbnail) {
    this.id = id;
    this.title = title;
    this.mediaUrl = mediaUrl;
    this.sourceUrl = sourceUrl;
    this.displayUrl = displayUrl;
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

  public String getMediaUrl() {
    return mediaUrl;
  }

  public String getSourceUrl() {
    return sourceUrl;
  }

  public String getDisplayUrl() {
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
