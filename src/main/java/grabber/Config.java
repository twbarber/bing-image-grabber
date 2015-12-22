package grabber;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tyler on 12/21/15.
 */
public class Config {

    public String getVersion() {
        java.io.InputStream is = this.getClass().getResourceAsStream("my.properties");
        java.util.Properties p = new Properties();
        try {
            p.load(is);
            return p.getProperty("version");
        } catch (IOException e) {
            return "Unkown";
        }
    }

}
