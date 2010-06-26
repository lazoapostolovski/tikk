package com.tikk.huffman;

import com.tikk.com.tikk.common.CharFrequencyGenerator;
import com.tikk.com.tikk.common.MessageCoder;
import com.tikk.com.tikk.common.CharItem;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Huffman {
  private List<CharItem> chList;
  private HuffmanCharCodeGenerator generator = new HuffmanCharCodeGenerator();
  private MessageCoder coder = new MessageCoder();

  @Before
  public void before(){
    chList = new CharFrequencyGenerator().generateList();
  }

  @Test
  public void testCodeAndDecodeMessage(){
    List<CharItem> local = new ArrayList<CharItem>();
    local.addAll(chList);

    Map<Character, String> generatedCodes = generator.generate(local);

    String message = "Български Манастир подробна информация за всички популярни български манастири. История, архитектура, снимки, настаняване, транспорт";

    System.out.println("Message for codding");
    System.out.println(message);
    Double messageBits = new Double(message.getBytes().length * 8);
    System.out.println("Message length: " + messageBits.intValue() + " bits");

    String coddedMessage = coder.codeMessage(generatedCodes, message);

    System.out.println("Codded message");
    System.out.println(coddedMessage);
    Double coddedMessageBits = new Double(coddedMessage.length());
    System.out.println("Codded message length: " + coddedMessageBits.intValue() + " bits");

    String deCoddedMessage = coder.decodeMessage(generatedCodes, coddedMessage);

    assertEquals(message, deCoddedMessage);

    System.out.println("Compression: " + (coddedMessageBits / messageBits) * new Double(100) + " %");
  }

  @Test
  public void testCodeMessage(){
    List<CharItem> local = new ArrayList<CharItem>();
    local.addAll(chList);

    Map<Character, String> generatedCodes = generator.generate(local);

    String codedMessage = coder.codeMessage(generatedCodes, "работи");

    assertEquals("0000110111001001110101111", codedMessage);
  }

  @Test
  public void testDecodeMessage(){
    List<CharItem> local = new ArrayList<CharItem>();
    local.addAll(chList);

    Map<Character, String> generatedCodes = generator.generate(local);

    String decodedMessage = coder.decodeMessage(generatedCodes, "0000110111001001110101111");

    assertEquals("работи", decodedMessage);
  }


}
