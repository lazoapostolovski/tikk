package com.tikk.shannonfano;

import com.tikk.com.tikk.common.CodeMapper;
import com.tikk.com.tikk.common.FrequencyAnalizer;
import com.tikk.com.tikk.common.CharItem;
import com.tikk.com.tikk.common.MessageCoder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ShannonFano {
  private FrequencyAnalizer analizer = new FrequencyAnalizer();

  private ShannonFanoAlgorithm shannonFanoAlgorithm = new ShannonFanoAlgorithm();

  private MessageCoder messageCoder = new MessageCoder();

  private CodeMapper codeMapper;

  @Before
  public void before() {
    codeMapper = new CodeMapper(shannonFanoAlgorithm);
  }

  @Test
  public void testCodeAndDecodeMessageWithShannonFanoAlgorithm() {
    List<CharItem> characterList = analizer.generateList();
    Map<Character, String> mappedCodes = codeMapper.mapCodes(characterList);

    String message ="Български Манастир подробна информация за всички популярни български манастири. История, архитектура, снимки, настаняване, транспорт";

    printMessage("Codding message: ", message);

    Double messageBits = new Double(message.getBytes().length * 8);

    printLength("Message size: ", messageBits);

    String coddedMessage = messageCoder.codeMessage(mappedCodes, message);

    Double coddedMessageBits = new Double(coddedMessage.length());
    printMessage("Codded message: ", coddedMessage);

    String decodedMessage= messageCoder.decodeMessage(mappedCodes, coddedMessage);

    printMessage("Decoded message", decodedMessage);

    printCompressionLevel(coddedMessageBits, messageBits);
  }

  @Test
  public void testCodeMessage() {
    List<CharItem> charFrequencies = analizer.generateList();

    Map<Character, String> generatedCodes = codeMapper.mapCodes(charFrequencies);

    String codedMessage = messageCoder.codeMessage(generatedCodes, "работи");

    assertEquals("0001111001011111001001100111", codedMessage);
  }

  @Test
  public void testDecodeMessage() {
    List<CharItem> charFrequencies = analizer.generateList();

    Map<Character, String> generatedCodes = codeMapper.mapCodes(charFrequencies);

    String decodedMessage = messageCoder.decodeMessage(generatedCodes, "0001111001011111001001100111");

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
