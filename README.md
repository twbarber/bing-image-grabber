JIG - Java Image Grabber
==================

[![Build Status](https://travis-ci.org/twbarber/jig.svg?branch=master)](https://travis-ci.org/twbarber/jig)

**Version:** 0.1-ALPHA

The Java Image Grabber library lets the user download images en masse with the help of
Microsoft's Bing search engine. Users have the option to provide parameters, or use 
default configuration values.

**Using JIG**

```java
// Load JIG Config and Bing Service
Config config = getConfig();
BingService bing = new BingService(config);

// Build Search Request
ImageRequest request = new ImageRequestBuilder()
    .setSearchTerm("cat")
    .setNumberOfImages(5)
    .buildRequest();

// Execute Search and Download Resulting Images
Collection<String> imageUrls = bing.search(request);
Collection<BufferedImage> images = bing.download(imageUrls);
```

**Required Configuration Information**

    - Bing Account Key

    See the example_config.properties for more information.

**Optional Query Parameters and Defaults**

    - Search Term : Randomly generated 7 Digit Number
    - Size of Return Set : 50
    - Adult Option : Strict
    - Market : None, determined by Bing using requesting IP
