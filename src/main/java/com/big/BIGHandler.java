package com.big;
import java.io.IOException;

public class BIGHandler {
	
	public KeyHandler myKeyHandler;
	public QueryBuilder myQueryuilder;
	public URLGrabber myUrlGrabber;
	public ImageDownloader myImageDownloader;
	
	public BIGHandler(String queryChoice, int numImages, String adult) throws IOException {
		
		myKeyHandler = new KeyHandler();
		myKeyHandler.getUserKey();
		
		
	}
}
