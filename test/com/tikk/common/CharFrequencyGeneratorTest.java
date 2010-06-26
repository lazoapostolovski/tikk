package com.tikk.common;

import com.tikk.com.tikk.common.CharFrequencyGenerator;
import com.tikk.com.tikk.common.CharItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CharFrequencyGeneratorTest {
  private CharFrequencyGenerator generator;
  private MockRealTextProvider textProvider;

  @Before
  public void before(){
    textProvider = new MockRealTextProvider();
    generator = new CharFrequencyGenerator(textProvider);
  }

  @Test
  public void testCalculateFrequencyOfCharacters(){
    textProvider.text = "aaabbb";
    List<CharItem> chars = generator.generateList();
    assertEquals(2, chars.size());
    assertEquals(new Double(50), chars.get(0).getWeight());
    assertEquals(new Double(50), chars.get(1).getWeight());
  }

  class MockRealTextProvider implements CharFrequencyGenerator.RealTextProvider {
    public String text;
    public String getRealText() {
      return text;
    }
  }
}
