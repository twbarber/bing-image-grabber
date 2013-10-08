bing-image-grabber
==================

Bing Image Grabber - Version 0.2.0

	It generates a JSON object with results from the Bing search, which
	we parse to extract the URLs of images. After that, they are downloaded
	and stored locally.

	Some of the code was used from the GitHub:

	https://github.com/mark-watson/bing_search_java/

	A query URL requires the following components:

		- Root Bing API URL, constant. See "rootURL" in makeQuery();
		- User's API Key, entered in test class
		- Search Term, we use a randomly generated integer
		- Query Type (Web, Images*, Video, etc.)

	The following parameters are optional, but we add them for completeness

		- Query Region, set as en-us for our searches
		- Content Filter, so no adult content... hopefully
		- Number of images per request.

	*In our case the search type is a constant, Images.

	The URL returns a JSON object which we parse to grab URLs of images that are
	returned using the search, The URLs are stored in an String array, and are then
	accessed by the saveImages() method. The size of the array is equal to the
	number of images requested per search term (imageCount parameter in the constructor).
	
		
	TODO 0.3.0: 
	
		- Default Download Directory
		
	TODO: 
	
		- GUI for standalone
		- Documentation
	
	DONE: 
	
		- Make "Random" query auto set parameters in Main
		- User key gets verified before running query
			-- Reimplemented key verification
		- Make log file for each query in imageDownloader
			-- Contains search term, results grabbed, list of URLs, time to download
		- Fix input issues in Main
		- Log file for previously used keys
		- Fisrt Time run files, populates Keys.text in user dir,

