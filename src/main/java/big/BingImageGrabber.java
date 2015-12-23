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

public class BingImageGrabber implements ImageGrabber {

	private final ImageDownloader.Config config;

	public BingImageGrabber(ImageDownloader.Config config) {
		this.config = config;
	}


	public void runBingImageGrabber() {
		System.out.println("\nBing Image Grabber " + config.getVersion() + "\n");
		displayMenus();
		authenticate();
		try {
			Collection<URL> bingURLs = buildQuery(this.queryTerm, this.numberImages, this.queryFilter);
			Collection<URL> imageURLs = getImageUrlList(bingURLs);
			downloadImages(imageURLs);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
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

	private void downloadImages(Collection<URL> imageUrls) { 
		ImageDownloader imageDownloader = new ImageDownloader(imageUrls);
		try {
			imageDownloader.downloadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<BufferedImage> getImages(String searchTerm) {
		return null;
	}

	@Override
	public Collection<BufferedImage> getImages(String searchTerm, int numberOfImages) {
		return null;
	}

	@Override
	public Collection<BufferedImage> getImages(String searchTerm, int numberOfImages, AdultOption adultOption) {
		return null;
	}

	@Override
	public Collection<BufferedImage> getImages(String searchTerm, int numberOfImages, AdultOption adultOption,
			Market market) {
		return null;
	}
}