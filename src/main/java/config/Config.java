package config;

/**
 * Created by Tyler on 12/23/15.
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