java-image-grabber
==================

JIG - Version 0.1

	The Java Image Grabber library lets the user download images from Microsoft's Bing search engine.
	Users have the option to provide parameters, or use default configuration values.

    Required Configuration Information

		- User's API Key

	Query Param

		- Search Term, we use a randomly generated integer
		- Query Type (Web, Images*, Video, etc.)

	The following parameters are optional, but we add them for completeness

		- Query Region, set as en-us for our searches
		- Adult Option, so no adult content... hopefully
		- Number of images per request.

	Defaults

	    - AdultOption : off
	    - Market : en-us
	    - Search Term : Randomly generated number
	    - Number of Images : 20

	*In our case the search type is a constant, Images.


	
