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
    showAllWords(myDictionary);
  }

  public static void main(String[] args) {
    Dictionary myDictionary = new Dictionary();
    dictionaryAdvanced(myDictionary);
    //DictionaryManagement.deleteWordFromDictionary("Z- beam", myDictionary);
    //DictionaryManagement.addWordsToDictionary(new Word("has", "là "), myDictionary);
    myDictionary.getWordList().sort(new Comparator<Word>() {
      @Override
      public int compare(Word w1, Word w2) {
        if (w1.getWord_target().toLowerCase().charAt(0) < w2.getWord_target().toLowerCase().charAt(0)) {
          return -1;
        } else if (w1.getWord_target().toLowerCase().charAt(0) > w2.getWord_target().toLowerCase().charAt(0)) {
          return 1;
        }
        return w1.getWord_target().toLowerCase().compareTo(w2.getWord_target().toLowerCase());
      }
    });
    //showAllWords(myDictionary);
    System.out.println(myDictionary.lookup("Disputes").getWord_explain());
    DictionaryManagement.dictionaryExportToFile(myDictionary);
  }
}
