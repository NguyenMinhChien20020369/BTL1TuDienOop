package Overall;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static Overall.DictionaryManagement.sortDictionary;

public class DictionaryCommandLine {

  public static void showAllWords(Dictionary myDictionary) {
    System.out.println("No   | English             | Vietnamese");
    for (int i = 0; i < myDictionary.getWordList().size(); i++) {
      System.out.println(i + 1 + "    | " + myDictionary.getWordList().get(i).getWord_target()
          + myDictionary.getWordList().get(i).getWord_explain());
    }
  }

  public static void dictionaryBasic(Dictionary myDictionary) {
    DictionaryManagement.insertFromCommandline(myDictionary);
    showAllWords(myDictionary);
  }

  public static Word dictionarySearcher(Word wordsToEdit, Dictionary myDictionary) {
    return myDictionary.getWordList().get(1);
  }
  public  static void dictionaryAdvanced(Dictionary myDictionary) {
//    ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
//    ArrayList<Word> arr = rd.read();
//    for(int i = 0 ; i < arr.size();i++){
//      myDictionary.addWord(arr.get(i));
//    }
   // showAllWords(myDictionary);
    DictionaryManagement.insertFromFile(myDictionary);
    //showAllWords(myDictionary);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Dictionary myDictionary = new Dictionary();
    dictionaryAdvanced(myDictionary);
//      String word = "Adjustable prop";
//      DictionaryManagement.deleteWordFromDictionary("Z- beam", myDictionary);
//    DictionaryManagement.dictionaryExportToFile(myDictionary);
//  int expl = myDictionary.searchWord(myDictionary,word);
//   String a1 = myDictionary.getWordList().get(expl).getWord_explain();
//
//      System.out.println(a1);
//********************************************************************//
    //DictionaryManagement.deleteWordFromDictionary("Z- beam", myDictionary);
    //DictionaryManagement.addWordsToDictionary(new Word("has", "là "), myDictionary);
    sortDictionary(myDictionary);
    //showAllWords(myDictionary);
    System.out.println(myDictionary.lookup("Disputes").getWord_explain());
    DictionaryManagement.dictionaryExportToFile(myDictionary);
  }
}
