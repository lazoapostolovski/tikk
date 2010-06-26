package com.tikk.common;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CharFrequencyGeneratorTest {
  private FrequencyAnalizer analizer;
  private MockRealTextProvider textProvider;

  @Before
  public void before(){
    textProvider = new MockRealTextProvider();
    analizer = new FrequencyAnalizer(textProvider);
  }

  @Test
  public void testCalculateFrequencyOfCharacters(){
    textProvider.text = "aaabbb";
    List<CharItem> chars = analizer.generateList();
    assertEquals(2, chars.size());
    assertEquals(new Double(50), chars.get(0).getWeight());
    assertEquals(new Double(50), chars.get(1).getWeight());
  }

  class MockRealTextProvider implements FrequencyAnalizer.RealTextProvider {
    public String text;
    public String getRealText() {
      return text;
    }
  }
}
