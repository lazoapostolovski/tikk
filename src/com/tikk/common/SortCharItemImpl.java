package com.tikk.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class SortCharItemImpl {
  // izvr6va brzo sortirane na bukvite v zavisimost koq bukva se sre6ta pove4e
  public static List<CharItem> sortList(List<CharItem> list) {
    Collections.sort(list, new Comparator<CharItem>() {
      public int compare(CharItem first, CharItem second) {
        if (first.getWeight().equals(second.getWeight())) {
          return 0;
        } else if (first.getWeight() > second.getWeight()) {
          return -1;
        }
        return 1;
      }
    });
    return list;
  }
}
