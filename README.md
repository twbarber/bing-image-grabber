JIG - Java Image Grabber
==================

[![Build Status](https://travis-ci.org/twbarber/jig.svg?branch=master)](https://travis-ci.org/twbarber/jig)

**Version:** 0.1

The Java Image Grabber library lets the user download images from Microsoft's Bing search engine.
Users have the option to provide parameters, or use default configuration values.

**Usage**

```java
// Creates new BingService for Search and Download
Config config = new Config(accountKey);
BingService bingService = new BingService(config);

// Create new Search Request. See below for default values
ImageRequestBuilder builder = new ImageRequestBuilder();
builder.setSearchTerm("cats");
ImageRequest request = builder.createRequest();

// ImageResponse holds the collection of results
ImageResponse response = bingService.search(request);
Collection<ImageResult> results = response.getResults();
```

**Required Configuration Information**

    - User's API Key

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