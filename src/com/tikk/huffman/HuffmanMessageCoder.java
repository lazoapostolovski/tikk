package com.tikk.huffman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class HuffmanMessageCoder {
  public String codeMessage(Map<Character, String> codes, String message) {
    char[] messageChars = message.toCharArray();

    String codedMessage = "";

    for(Character ch : messageChars){
      String code = codes.get(ch);
      codedMessage = codedMessage + code;
    }
    return codedMessage;
  }

  public String decodeMessage(Map<Character, String> generatedCodes, String code) {
    // flip map for easy find the char for the code. its low cost operation if codes is below 10 000
    Map<String, Character> flipMap = flipMap(generatedCodes);
    String buffer = "";
    String message = "";
    for(int i=0; i<code.length(); i++){
      // add char to buffer
      buffer = buffer + code.charAt(i);

      // if we have code for that character, get that character and append to the message. then clean buffer.
      if(flipMap.containsKey(buffer)){
        message = message + flipMap.get(buffer);
        buffer = "";
      }
    }
    return message;
  }

  private Map<String, Character> flipMap(Map<Character, String> generatedCodes){
    Map<String, Character> flipMap = new HashMap<String, Character>();

    for(Map.Entry<Character, String> chSet: generatedCodes.entrySet()){
      flipMap.put(chSet.getValue(), chSet.getKey());
    }

    return flipMap;
  }
}
