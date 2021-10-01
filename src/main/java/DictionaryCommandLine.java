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

  public static Word dictionarySearcher(Word wordsToEdit, Dictionary myDictionary) {
    return myDictionary.getWordList().get(1);
  }

  public static void main(String[] args) {
    Dictionary myDictionary = new Dictionary();
    dictionaryBasic(myDictionary);
    Scanner sc = new Scanner(System.in);
    Word wordsToDel = new Word();
    wordsToDel.setWord_target(sc.nextLine());
    wordsToDel.setWord_explain(sc.nextLine());
    DictionaryManagement.DelWordFormCommandLine(wordsToDel, myDictionary);
    showAllWords(myDictionary);
  }
}
