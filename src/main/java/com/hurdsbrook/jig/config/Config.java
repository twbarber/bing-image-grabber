package com.hurdsbrook.jig.config;

import com.hurdsbrook.jig.constants.AdultOption;
import com.hurdsbrook.jig.constants.Market;
import com.sun.javafx.tools.packager.Log;
import org.apache.log4j.Logger;

/**
 * Created by Tyler on 12/23/15.
 */
public class Config {

  private Logger logger = Logger.getLogger(Config.class);
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