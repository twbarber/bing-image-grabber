package big;

/**
 * Created by tyler on 12/21/15.
 */
public class Config {

    private final String apiKey;

    public Config(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return this.apiKey;
    }
}
