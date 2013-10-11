import java.io.IOException;


public class bigHandler {
	
	public keyHandler myKeyHandler;
	public queryBuilder myQueryuilder;
	public urlGrabber myUrlGrabber;
	public imageDownloader myImageDownloader;
	
	public bigHandler(String queryChoice, int numImages, String adult) throws IOException {
		
		myKeyHandler = new keyHandler();
		myKeyHandler.getUserKey();
		
		
	}
}
