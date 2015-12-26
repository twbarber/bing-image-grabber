package jig.util;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Various utilities for working with strings in JIG.
 */
public class StringUtils {

  private static Logger logger = Logger.getLogger(StringUtils.class);
  public static boolean isNotNullOrEmpty(String s) {
    return !(s == null || s.isEmpty());
  }

  public static URL convertToUrl(String s) {
    URL converted = null;
    try {
      converted = new URL(s);
    } catch (MalformedURLException e) {
      logger.error("Bad URL");
    }
    return converted;
  }
}
