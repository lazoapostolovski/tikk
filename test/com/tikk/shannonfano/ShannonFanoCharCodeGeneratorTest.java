package com.tikk.shannonfano;

import com.tikk.common.CharItem;
import com.tikk.common.SortCharItemImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ShannonFanoCharCodeGeneratorTest {
  private ShannonFanoAlgorithm generator;

  @Before
  public void before(){
    generator = new ShannonFanoAlgorithm();
  }

  @Test
  public void testGenerateListWithCharCodes(){

    List<CharItem> charFrequencies = getListWithCharItems();
    charFrequencies = SortCharItemImpl.sortList(charFrequencies);
    charFrequencies = generator.generateCodes(charFrequencies, 100d);

    assertNotNull(charFrequencies);

    for(CharItem chi : charFrequencies){
      System.out.println(chi.getCh() + " : " + chi.getCode());
    }
  }

  private List<CharItem> getListWithCharItems(){
    List<CharItem> list = new ArrayList<CharItem>();
    list.add(CharItem.with('К', 5d));
    list.add(CharItem.with('Д', 10d));
    list.add(CharItem.with('Н', 15d));
    list.add(CharItem.with('А', 5d));
    list.add(CharItem.with('Е', 25d));
    list.add(CharItem.with('Р', 10d));
    list.add(CharItem.with('П', 5d));
    list.add(CharItem.with('С', 10d));
    list.add(CharItem.with(' ', 15d));

    return list;
  }
}
