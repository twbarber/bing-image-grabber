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


}