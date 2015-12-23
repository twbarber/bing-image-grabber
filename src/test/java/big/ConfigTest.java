package big;


import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


/**
 * Created by tyler on 12/21/15.
 */
public class ConfigTest {

    private Config configUnderTest = new Config();

    @Test
    public void testGetVersion() throws Exception {
        assertEquals("0.3.0-SNAPSHOT", this.configUnderTest.getVersion());
    }

    private String getVersion() {
        try {
            InputStream is = ConfigTest.class.getResourceAsStream("/app.properties");
            Properties p = new Properties();
            p.load(is);
            return p.getProperty("app.version");
        } catch (Exception e) {
            return "Unknown";
        }
    }

}
