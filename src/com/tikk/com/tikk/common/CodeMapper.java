package com.tikk.com.tikk.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CodeMapper {
  private CoddingAlgorithm algorithm;

  public CodeMapper(CoddingAlgorithm algorithm) {
    this.algorithm = algorithm;
  }

  public Map<Character, String> mapCodes(List<CharItem> chars) {
    // generira kodovi za site bukvi. kodot se generira od algoritamot koj mu se podava
    List<CharItem> characters = algorithm.generateCodes(chars, 100d);
    Map<Character, String> mappedCharacters = new HashMap<Character, String>();

    // site bukvi se zimaat edna po edna i se slagaat vo map za po lesen dostap od bukva do bukva
    // izgledaat nesto taka
    //    a  | 101
    //    b |  100
    // ...........
    // se slagaat taka za polesen dostap
    for (CharItem charItem : characters) {
      mappedCharacters.put(charItem.getCh(), charItem.getCode());
    }

    return mappedCharacters;
  }
}
