package jig.util;

import org.apache.log4j.Logger;

/**
 * Various utilities for working with strings in JIG.
 */
public class StringUtils {

  private static Logger logger = Logger.getLogger(StringUtils.class);

  public static boolean isNotNullOrEmpty(String s) {
    return !(s == null || s.isEmpty());
  }

}
