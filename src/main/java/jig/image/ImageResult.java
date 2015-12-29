package jig.image;

import com.google.gson.annotations.SerializedName;

/**
 * Stores the POJO version of an Image Result from the Bing Search API.
 */
public class ImageResult {

  @SerializedName("ID")
  private String id;
  @SerializedName("Title")
  private String title;
  @SerializedName("MediaUrl")
  private String mediaUrl;
  @SerializedName("SourceUrl")
  private String sourceUrl;
  @SerializedName("DisplayUrl")
  private String displayUrl;
  @SerializedName("Width")
  private int width;
  @SerializedName("Height")
  private int height;
  @SerializedName("FileSize")
  private int fileSize;
  @SerializedName("ContentType")
  private String contentType;
  @SerializedName("Thumbnail")
  private Thumbnail thumbnail;

  public ImageResult(String id, String title, String mediaUrl, String sourceUrl, String displayUrl, int width,
      int height, int fileSize, String contentType, Thumbnail thumbnail) {
    this.id = id;
    this.title = title;
    this.mediaUrl = mediaUrl;
    this.sourceUrl = sourceUrl;
    this.displayUrl = displayUrl;
    this.width = width;
    this.height = height;
    this.fileSize = fileSize;
    this.contentType = contentType;
    this.thumbnail = thumbnail;
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

  public Thumbnail getThumbnail() {
    return thumbnail;
  }

}
