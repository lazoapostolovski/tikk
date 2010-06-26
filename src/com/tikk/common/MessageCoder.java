package com.tikk.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class MessageCoder {

  // vrsi kodirane na soop6tenieto. mu se podava map so bukvi i kodovi za sekoja bukva i soop6tenie koe treba da se kodira
  public String codeMessage(Map<Character, String> codes, String message) {
    // razbiva soop6tenieto na bukvi
    char[] messageChars = message.toCharArray();

    // tuka ke se pribavat kodovete za vsqka bukva.
    String codedMessage = "";

    // se vzima bukva po bukva od soop6tenieto
    for(Character ch : messageChars){
      String code = codes.get(ch);       // se zima koda za bukvata od mapa (tablicata)
      codedMessage = codedMessage + code; // i kodot se pribava na kraja od kodiranoto soop6tenie
    }

    // se vr6ta kodiranoto soop6tenie
    return codedMessage;
  }

  public String decodeMessage(Map<Character, String> generatedCodes, String code) {
    // se prevrtva mapa za po lesno da se ima dostap do kodovete i bukvite koito odgovaraat na tqh

    Map<String, Character> flipMap = flipMap(generatedCodes);

    String buffer = ""; // tuk ke se vzimaat bitovite od kodiranoto soop6tenie i ke se pribavqt edin sled drug se dokato ne se nameri bukva s takav kod
                        // kogato se nameri takav kod tova se is4istva

    String message = ""; // tuk 6te se formira soop6tenieto bukva po bukva

    for(int i=0; i<code.length(); i++){
      // add char to buffer
      buffer = buffer + code.charAt(i);// se vzimat posledovatelno btovete na kodiranoto soop6tenie i se pribavjaat v buffera

      // ako imame bukva s takav kod
      if(flipMap.containsKey(buffer)){
        message = message + flipMap.get(buffer); // bukvata se pribava kam soop6tenieto
        buffer = ""; // i se za4istva buffera
      }
    }
    // naj nakraja se vr6ta dekodiranoto soop6tenie
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
