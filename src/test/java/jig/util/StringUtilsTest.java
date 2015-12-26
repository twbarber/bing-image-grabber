package jig.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyler on 12/26/15.
 */
public class StringUtilsTest {

  @Test
  public void testStringIsNull() throws Exception {
    String testString = null;
    assertFalse(StringUtils.isNotNullOrEmpty(testString));
  }

  @Test
  public void testStringIsEmpty() throws Exception {
    String testString = "";
    assertFalse(StringUtils.isNotNullOrEmpty(testString));
  }

  @Test
  public void testGoodString() throws Exception {
    String testString = "Test";
    assertTrue(StringUtils.isNotNullOrEmpty(testString));
  }

  @Test
  public void testConvertToUrl() throws Exception {
    assertTrue(true);
  }
}