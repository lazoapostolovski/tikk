package com.tikk.huffman;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CharItem {
  public static CharItem with(Character ch, Double weight){
    CharItem cw = new CharItem();
    cw.ch = ch;
    cw.weight = weight;
    return cw;
  }

  public static CharItem with(CharItem parentOne, CharItem parentTwo, Double weight){
    CharItem charCode = new CharItem();
    charCode.parentOne = parentOne;
    charCode.patentTwo = parentTwo;
    charCode.weight = weight;
    return charCode;
  }

  private Character ch;
  private Double weight;
  private String code = "";
  private CharItem parentOne;
  private CharItem patentTwo;

  private CharItem(){
  }

  public Character getCh() {
    return ch;
  }

  public Double getWeight() {
    return weight;
  }

  public void setCh(Character ch) {
    this.ch = ch;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int compareTo(CharItem charCode) {
    return weight.intValue();
  }

  public CharItem getParentOne() {
    return parentOne;
  }

  public void setParentOne(CharItem parentOne) {
    this.parentOne = parentOne;
  }

  public CharItem getPatentTwo() {
    return patentTwo;
  }

  public void setPatentTwo(CharItem patentTwo) {
    this.patentTwo = patentTwo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CharItem that = (CharItem) o;

    if (ch != null ? !ch.equals(that.ch) : that.ch != null) return false;
    if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = ch != null ? ch.hashCode() : 0;
    result = 31 * result + (weight != null ? weight.hashCode() : 0);
    return result;
  }
}
