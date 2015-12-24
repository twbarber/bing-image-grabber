package com.hurdsbrook.jig.image;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/*
    Bing Image Grabber - Version 0.2.2
 */

public class ImageDownloader {

	private Logger logger = Logger.getLogger(ImageDownloader.class);

	public Collection<BufferedImage> downloadImages(Collection<URL> imagesToDownload) {
		return new ArrayList<BufferedImage>();
	}


}