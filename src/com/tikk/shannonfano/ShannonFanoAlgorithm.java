package com.tikk.shannonfano;

import com.tikk.com.tikk.common.CharItem;
import com.tikk.com.tikk.common.CoddingAlgorithm;
import com.tikk.com.tikk.common.SortCharItemImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ShannonFanoAlgorithm implements CoddingAlgorithm{
  
  public List<CharItem> generateCodes(List<CharItem> charFrequencies, double listPercents) {
    // kogato ostane samo edin element vo listata spri i vrni se nazad
    if(charFrequencies.size() == 1){
      return charFrequencies;
    }

    // se sadarza kolko sa procentite v parvata 4ast
    Double firstHalfPercents = 0d;
    List<CharItem> firstHalf = getFirstHalf(charFrequencies, listPercents);

    // se iterira prvata polovina
    for(CharItem first : firstHalf){
      String currentCode = first.getCode();// se vzima koda na bukvata koito e formiran do sega
      first.setCode(currentCode + "0"); // mu se dodava nula
      firstHalfPercents += first.getWeight();// i se presmetuva zbirot na cestotite vo prvata polovina
    }
    // se sadrza kolko sa procentite vo vtorata polovina
    Double secondHalfPercents = 0d;
    for(CharItem second : charFrequencies){
      String currentCode = second.getCode();// se vzima koda na bukvata koj sto kod e formiran do sega
      second.setCode(currentCode + "1"); // mu se dodava nula
      secondHalfPercents += second.getWeight();// se presmetuva zbirot na cestotite vo vtorata polovina
    }
    List<CharItem> returnList = new ArrayList<CharItem>();

    // povtorno rekursivno se izvikuva metoda dva pati, ednas mu se podava prvata polovina
    returnList.addAll(generateCodes(firstHalf, firstHalfPercents));
    // a vtoriot pat mu se dodava vtorata polovina
    returnList.addAll(generateCodes(charFrequencies, secondHalfPercents));

    return returnList;
  }

  // gi zema polovinata od lementite na koi sto zbirot od cestotite im e po blizok do polovinata na vsi4kite procenti
  private List<CharItem> getFirstHalf(List<CharItem> allChars, double percents) {
    // pazi kolko procenti sa sobrani do tuk
    Double currentSize = 0d;

    // opredelq kade e polovinata na procentite koito sa podadeni
    Double halfSize = percents / 2;

    // kazva kolko elementa da badat vzeti ot vsi4kite i da badat slozeni v parvata 4ast
    int pageSize = 0;

    for (int i = 0; i < allChars.size(); i++) {
      // go vzimame element ot vsi4kite
      CharItem charItem = allChars.get(i);

      // vzemame na procentite kolko se stre6ta bukvata
      Double weight = charItem.getWeight();

      // procentite se slagaat na vsi4ki procenti koito sa sobrani do sega
      Double nextSize = currentSize + weight;

      // ako procentite sled pribavane na procentite na teku6tiq element sa po golemi od polovinata
      if (nextSize >= halfSize) {
        // se vr6i proverka so procentite na koj element ke bide po blizu do polovinata
        Double current = halfSize - currentSize;
        Double next = nextSize - halfSize;

        // ako se dobie negativen broj sekogas da se napravi pozitiven
        if (next < 0) {
          next = next * -1;
        }

        // ako so dodavane na predisniot element sme poblisku do sredinata spirame i gi simame elementite do predisniot za prva polovina
        if (current < next) { // se pravi proverka dali predisnija ili slednija e po blisku do polovinata
          pageSize = i - 1; // se kazuva da se zemat elementite do predniot element
        } else {      // ili
          pageSize = i; // se zimaat elementite do teku6tiot element
        }

        // se spira cikala
        break;
      } else {
        // ako se uste ne e nadminata polovinata zgolemi gi procentite. i prodolzi so sledniot element
        currentSize = nextSize;
      }
    }

    // se simaat elementite koj se opredeleni za prvata polovina (pageSize) i se stavaat vo druga lista a se mahaat od vsi4kite
    List<CharItem> firstHalf = new ArrayList<CharItem>();
    for(int i=0; i<=pageSize; i++){
      firstHalf.add(allChars.remove(0));
    }

    // se vraka prvata polovina
    return firstHalf;
  }
}
