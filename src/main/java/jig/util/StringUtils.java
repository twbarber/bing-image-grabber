package jig.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tyler on 12/25/15.
 */
public class StringUtils {

  public static boolean isNullOrEmpty(String s) {
    return (s == null || s.isEmpty());
  }

  public static URL convertToUrl(String s) {
    try {
      URL imageRequestUrl = new URL(s);
      return  imageRequestUrl;
    } catch (MalformedURLException e) {

    }
  }
}
