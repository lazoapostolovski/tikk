package com.tikk.huffman;

import com.tikk.common.SortCharItemImpl;
import com.tikk.common.CharItem;
import com.tikk.common.CoddingAlgorithm;

import java.util.*;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class HuffmanAlgorithm implements CoddingAlgorithm {

  // za vsi4ki podadeni bukvi generira kod so rekursija
  public List<CharItem> generateCodes(List<CharItem> chars, double percents){
    // se podrezdaat bukvite po tova koq bukva po 4esto se sre6ta
    sortList(chars);

    // koga ke ostanat samo dva elementa im se pribavat poslednite bita od koda i se vr6ta nazad
    if(chars.size() == 2){
      chars.get(0).setCode("0");
      chars.get(1).setCode("1");
      return chars;
    }

    // se mahaat ot vsi4kite elementi poslednite dva elementa koito se sre6tat nai malko
    CharItem lastOne = chars.remove(chars.size()-1);
    CharItem lastTwo = chars.remove(chars.size()-1);

    // se sazdava nov element koito sodarza informacija od koj elementi e sozdaden
    CharItem child = CharItem.with(lastOne, lastTwo, lastOne.getWeight() + lastTwo.getWeight());

    // se pribava novija element kam vsi4kite
    chars.add(child);

    // se izvikva samija metod rekursivno....
    List<CharItem> returnedList = generateCodes(chars, percents);

    // kogato metoda dojde do kraja

    // na poslednite dva elementa koito beha mahnati im se dodava koda koito e vrnat i im se dodava o6te po edin bit
    lastOne.setCode(child.getCode()+1);
    lastTwo.setCode(child.getCode()+0);

    // se maha noviq element koito pogore be6e sozdaden ot listata s vsi4ki elementi
    returnedList.remove(child);

    // i slet tova povtorno se vr6taat nazad dvata elementa koito beha mahnati
    returnedList.add(lastOne);
    returnedList.add(lastTwo);

    return chars;
  }

  private void sortList(List<CharItem> list){
    list = SortCharItemImpl.sortList(list);
  }

  
}
