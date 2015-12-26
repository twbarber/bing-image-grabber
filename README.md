JIG - Java Image Grabber
==================

Version 0.1

The Java Image Grabber library lets the user download images from Microsoft's Bing search engine.
Users have the option to provide parameters, or use default configuration values.

Required Configuration Information

    - User's API Key

Optional Query Parameters

    - Search Term
    - Size of Return Set
    - Adult Option

Default Values if not Specified

    - Search Term: Randomly generated 7 Digit Number
    - Size of Return Set: 50
    - Adult Option: Strict

Market

    - By default, Bing will try to determine the Market via the connecting IP address.
        Currently, there's no way to override this value.
