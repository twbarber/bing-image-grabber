package com.hurdsbrook.jig.bing;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Used to download Images gathered by the ImageFinder.
 */
public class ImageDownloader {

	private Logger logger = Logger.getLogger(ImageDownloader.class);

	public Collection<BufferedImage> downloadImages(Collection<URL> imagesToDownload) {
		return new ArrayList<BufferedImage>();
	}


}