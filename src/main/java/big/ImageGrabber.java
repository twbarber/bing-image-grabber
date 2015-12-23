package big;

import java.awt.image.BufferedImage;
import java.util.Collection;

/**
 * Created by Tyler on 12/23/15.
 */
public interface ImageGrabber {

    public Collection<BufferedImage> getImages(String searchTerm);

    public Collection<BufferedImage> getImages(String searchTerm, int numberOfImages);

    public Collection<BufferedImage> getImages(String searchTerm, int numberOfImages, AdultOption adultOption);

    public Collection<BufferedImage> getImages(String searchTerm, int numberOfImages,
        AdultOption adultOption, Market market);

}

