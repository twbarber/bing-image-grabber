package grabber;


import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


/**
 * Created by tyler on 12/21/15.
 */
public class ConfigTest {

    private Config configUnderTest = new Config();

    @Test
    public void testGetVersion() throws Exception {
        assertEquals(getVersion(), this.configUnderTest.getVersion());
    }

    private String getVersion() {
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
