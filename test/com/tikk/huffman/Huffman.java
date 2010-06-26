package com.tikk.huffman;

import com.tikk.common.FrequencyAnalizer;
import com.tikk.common.CodeMapper;
import com.tikk.common.MessageCoder;
import com.tikk.common.CharItem;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Huffman {
  // generator koito pravi 4estoten analiz na bukvite i za vsqka bukva pazi v procenti kolko pati se sre6ta
  private FrequencyAnalizer analizer = new FrequencyAnalizer();

  // algoritama na huffman koito za vsqka bukva vav zavisimost ot kolko pati se sre6ta generira kod (primer: 1101)
  private HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();

  // koder koito go kodira i go dekodira soop6tenieto
  private MessageCoder messageCoder = new MessageCoder();

  // mapva vsqka bukva kam soodvetniq kod.
  private CodeMapper codeMapper;

  @Before
  public void before(){
    // vo konstruktora se podava algoritama koito 6te se polzva za generirane na kodovite
    codeMapper = new CodeMapper(huffmanAlgorithm);
  }

  @Test
  public void testCodeAndDecodeMessage(){
    // se pravi 4estoten analiz za vsqka bukva
    List<CharItem> characterList = analizer.generateList();

    // tozi 4estoten analiz se podava za da se generirat kodovi i da se mapnat te kodovi kam bukvite
    Map<Character, String> mappedCodes = codeMapper.mapCodes(characterList);

    // soop6tenieto koeto trqbva da se kodira
    String message = "Български Манастир подробна информация за всички популярни български манастири. История, архитектура, снимки, настаняване, транспорт";
    // pe4atane na soop6tenieti
    printMessage("Message for codding: ", message);

    // dolzina na soop6tenieto v bitove (nuli i edinici)
    Double messageBits = new Double(message.getBytes().length * 8);

    // pe4atane na dolzinata na soop6tenieto
    printLength("Message length: ",messageBits);

    // kodirane na soop6tenieto
    String coddedMessage = messageCoder.codeMessage(mappedCodes, message);

    // pe4atane na kodiranoto soop6tenie
    printMessage("Codded message: ", coddedMessage);

    // dolzina na kodiranoto soop6tenie v bitove (nuli i edinici)
    Double coddedMessageBits = new Double(coddedMessage.length());
    // pecatanje na dolzinata na soop6tenieto
    printLength("Codded message length: ", coddedMessageBits);

    // dekodirane na soop6tenieto
    String deCoddedMessage = messageCoder.decodeMessage(mappedCodes, coddedMessage);
    // pe4atane
    printMessage("Decoded message:", deCoddedMessage);

    // proverka dali podadenoto soop6tenie i polu4enoto sa s6ti
    assertEquals(message, deCoddedMessage);

    // pe4atane na nivoto na kompresiq
    printCompressionLevel(coddedMessageBits, messageBits);
  }

  @Test
  public void testCodeMessage(){
    List<CharItem> characterList = analizer.generateList();

    Map<Character, String> mappedCodes = codeMapper.mapCodes(characterList);

    String codedMessage = messageCoder.codeMessage(mappedCodes, "работи");

    assertEquals("0000110111001001110101111", codedMessage);
  }

  @Test
  public void testDecodeMessage(){
    List<CharItem> characterList = analizer.generateList();

    Map<Character, String> mappedCodes = codeMapper.mapCodes(characterList);

    String decodedMessage = messageCoder.decodeMessage(mappedCodes, "0000110111001001110101111");

    assertEquals("работи", decodedMessage);
  }

  private void printMessage(String text, String message){
    System.out.println(text);
    System.out.println(message);
    System.out.println("");
  }

  private void printLength(String text, Double bits){
    System.out.println(text + bits.intValue() + " bits");
    System.out.println("");
  }

  private void printCompressionLevel(Double coddedMessageBits, Double messageBits){
    System.out.println("Compression: " + (100d - (coddedMessageBits / messageBits) * new Double(100)) + " %");
  }
}
