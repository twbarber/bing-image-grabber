package big;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/*
    Bing Image Grabber - Version 0.2.2
 */

public class BingImageDownloader implements ImageDownloader {

	@Override
	public Collection<BufferedImage> downloadImages(Collection<URL> imagesToDownload) {
		return new ArrayList<BufferedImage>();
	}

	private void authenticate() {
		KeyHandler keyHandler = new KeyHandler();
		try {
			String userKey = keyHandler.getExistingKey();
			while (keyHandler.isValidKey(userKey)) {
				userKey = this.mainMenu.keyMenu();
				if (keyHandler.isValidKey(userKey)) {
					keyHandler.writeKey(userKey);
					this.encryptedKey = userKey;
					return;
				}
			}
		} catch (IOException e) {
			System.err.println("There was an error authenticating your key.");
		}
	}

	private Collection<URL> buildQuery(String queryTerm, int numberImages, String queryFilter) throws MalformedURLException {
		QueryBuilder queryBuilder = new QueryBuilder(queryTerm, numberImages, queryFilter);
		queryBuilder.encodeParameters();
		return queryBuilder.generateQuerys();
	}

	private Collection<URL> getImageUrlList(Collection<URL> bingURLs) {
		ArrayList<URL> imageURLList = new ArrayList<URL>();
		UrlGrabber urlGrabber = new UrlGrabber(this.encryptedKey);
		for (URL aBingURL : bingURLs) {
			try {
				String jsonAsString = urlGrabber.runQuery(aBingURL);
				imageURLList.addAll(urlGrabber.parseURLs(jsonAsString));
			} catch (Exception e) {
				System.err.println("There was a problem getting the image URLs.");
			}
		}
		return imageURLList; 
	}


}