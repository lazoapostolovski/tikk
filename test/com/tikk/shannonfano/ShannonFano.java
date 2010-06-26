package com.tikk.shannonfano;

import com.tikk.com.tikk.common.CharFrequencyGenerator;
import com.tikk.com.tikk.common.CharItem;
import com.tikk.com.tikk.common.MessageCoder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ShannonFano {
  private ShannonFanoCharCodeGenerator codesGenerator = new ShannonFanoCharCodeGenerator();
  private CharFrequencyGenerator charFrequencyGenerator = new CharFrequencyGenerator();
  private MessageCoder coder = new MessageCoder();

  @Test
  public void testCodeAndDecodeMessageWithShannonFanoAlgorithm(){
    List<CharItem> charFrequencies = charFrequencyGenerator.generateList();
    Map<Character, String> mappedCharacterCodes = codesGenerator.generate(charFrequencies);

    String plainText = "Известните базови криптографски алгоритми се класифицират по " +
        "различни класификационни признаци. Като класификационен признак на първото ниво на класификацията е " +
        "използвано свойството симетричност на алгоритмите. ";

    Double messageBits = new Double(plainText.getBytes().length * 8);
    System.out.println("Codding message: ");
    System.out.println(plainText);
    System.out.println("Message size: " + messageBits.intValue() + " bits");

    String coddedMessage = coder.codeMessage(mappedCharacterCodes,plainText);

    Double coddedMessageBits = new Double(coddedMessage.length());
    System.out.println("Codded message: " + coddedMessage);
    System.out.println("Codded message size: " + coddedMessageBits.intValue());

    System.out.println("Compresion: " +  + (coddedMessageBits / messageBits) * new Double(100) + " %");
  }

    @Test
  public void testCodeMessage(){
    List<CharItem> charFrequencies = charFrequencyGenerator.generateList();

    Map<Character, String> generatedCodes = codesGenerator.generate(charFrequencies);

    String codedMessage = coder.codeMessage(generatedCodes, "работи");

    assertEquals("1010000111100110000111010", codedMessage);
  }

  @Test
  public void testDecodeMessage(){
    List<CharItem> charFrequencies = charFrequencyGenerator.generateList();

    Map<Character, String> generatedCodes = codesGenerator.generate(charFrequencies);

    String decodedMessage = coder.decodeMessage(generatedCodes, "1010000111100110000111010");

    assertEquals("работи", decodedMessage);
  }
}
