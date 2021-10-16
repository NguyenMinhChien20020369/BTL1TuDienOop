import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandLine {

  public static void showAllWords(Dictionary myDictionary) {
    System.out.println("No   | English             | Vietnamese");
    for (int i = 0; i < myDictionary.getWordList().size(); i++) {
      System.out.println(i + 1 + "    | " + myDictionary.getWordList().get(i).getWord_target()
          + "               | " + myDictionary.getWordList().get(i).getWord_explain());
    }
  }

  public static void dictionaryBasic(Dictionary myDictionary) {
    DictionaryManagement.insertFromCommandline(myDictionary);
    showAllWords(myDictionary);
  }

  public static void dictionaryAdvanced(Dictionary myDictionary) {
    DictionaryManagement.insertFromFile(myDictionary);
    //showAllWords(myDictionary);
    //DictionaryManagement.dictionaryLookup();
  }

  public static void main(String[] args) {
    Dictionary myDictionary = new Dictionary();
    dictionaryAdvanced(myDictionary);
    //DictionaryManagement.deleteWordFromDictionary("Z- beam", myDictionary);
    /*DictionaryManagement.addWordsToDictionary(new Word("has", "là"), myDictionary);
    showAllWords(myDictionary);*/
    //System.out.println(myDictionary.lookup("dummy test").getWord_explain());
    DictionaryManagement.dictionarySearcher(myDictionary);
    DictionaryManagement.dictionaryExportToFile(myDictionary);
  }
}
