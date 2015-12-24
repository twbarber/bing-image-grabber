package jig.config;

import jig.constants.AdultOption;
import jig.constants.Market;

/**
 * Created by Tyler on 12/23/15.
 */
public class Config {

  private final String apiKey;

  public Config(String apiKey) {
    this.apiKey = apiKey;
    loadDefaults();
  }

  private void loadDefaults() {

  }

  public String getApiKey() {
    return this.apiKey;
  }

  class ConfigDefaults {

    public int IMAGE_COUNT = 20;
    public Market MARKET = Market.EN_US;
    public AdultOption ADULT_OPTION = AdultOption.MODERATE;
    public String CONFIG_DIR = "";

  }
}