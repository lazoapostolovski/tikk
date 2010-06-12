package com.tikk.huffman;

import java.util.*;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CharCodeGenerator {

  public Map<Character, String> generate(List<CharItem> chars){
    List<CharItem> characters = generateList(chars);
    Map<Character, String> mappedCharacters = new HashMap<Character, String>();
    for(CharItem charItem : characters){
      mappedCharacters.put(charItem.getCh(), charItem.getCode());
    }
    return mappedCharacters;
  }

  protected List<CharItem> generateList(List<CharItem> chars){
    // sort
    sortList(chars);

    // stop when size of list is 2
    if(chars.size() == 2){
      chars.get(0).setCode("0");
      chars.get(1).setCode("1");
      return chars;
    }

    // remove last smallest and add child formed by them
    CharItem lastOne = chars.remove(chars.size()-1);
    CharItem lastTwo = chars.remove(chars.size()-1);
    CharItem child = CharItem.with(lastOne, lastTwo, lastOne.getWeight() + lastTwo.getWeight());

    chars.add(child);

    // get list and recover the code.
    List<CharItem> returnedList = generateList(chars);

    lastOne.setCode(child.getCode()+1);
    lastTwo.setCode(child.getCode()+0);

    returnedList.remove(child);
    returnedList.add(lastOne);
    returnedList.add(lastTwo);

    return chars;
  }

  private void sortList(List<CharItem> list){
    Collections.sort(list, new Comparator<CharItem>(){
      public int compare(CharItem first, CharItem second) {
        if(first.getWeight().equals(second.getWeight())){
          return 0;
        } else if(first.getWeight() > second.getWeight()){
          return -1;
        }
        return 1;
      }
    });
  }
}
