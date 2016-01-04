JIG - Java Image Grabber
==================

[![Build Status](https://travis-ci.org/twbarber/jig.svg?branch=master)](https://travis-ci.org/twbarber/jig)

**Version:** 0.1-ALPHA

The Java Image Grabber library lets the user download images en masse with the help of
Microsoft's Bing search engine. Users have the option to provide parameters, or use 
default configuration values.

**Using JIG**

```java
// Load JIG Config
Config config = getConfig();
BingService bing = new BingService(config);

// Build Search Request and Execute
ImageRequestBuilder builder = new ImageRequestBuilder()
    .setSearchTerm("cat")
    .setNumberOfImages(5);
ImageRequest request = builder.buildRequest();

// Execute Search and Download Resulting Images
Collection<BufferedImage> images = new ArrayList<>();
ImageResponse response = bing.search(request);
ImageDownloader downloader = new ImageDownloader();
images.addAll(downloader.downloadImages(response.getImage1Urls()));

// Draw all Downloaded Images
for (BufferedImage image : images) {
  drawImage(image);
}
```

**Required Configuration Information**

    - User's API Key

    See the example_config.properties for more information.

**Optional Query Parameters**

    - Search Term
    - Size of Return Set
    - Adult Option
    - Market

**Default Values if not Specified**

    - Search Term: Randomly generated 7 Digit Number
    - Size of Return Set: 50
    - Adult Option: Strict
    - Market: None, determined by Bing using requesting IP