package jig.image;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tyler on 12/28/15.
 */
public class Thumbnail {

  @SerializedName("MediaUrl")
  private String mediaUrl;
  @SerializedName("ContentType")
  private String contentType;
  @SerializedName("Width")
  private int width;
  @SerializedName("Height")
  private int height;
  @SerializedName("FileSize")
  private int fileSize;

  public Thumbnail(String mediaUrl, String contentType, int width, int height, int fileSize) {
    this.mediaUrl = mediaUrl;
    this.contentType = contentType;
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

}
