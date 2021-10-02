import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
  protected Dictionary dictionary = new Dictionary();
  public static void insertFromCommandline(Dictionary myDictionary) {
    Scanner sc = new Scanner(System.in);
    int numberOfWords = sc.nextInt();
    System.out.println(" Ban vua nhap vao: " + numberOfWords);
    Word temp = new Word();
    temp.setWord_target(sc.nextLine());
    for (int i = 0; i < numberOfWords; i++) {
      Word tempWord = new Word();
      tempWord.setWord_target(sc.nextLine());
      System.out.println(" Ban vua nhap vao: " + tempWord.getWord_target());
      tempWord.setWord_explain(sc.nextLine());
      System.out.println(" Ban vua nhap vao: " + tempWord.getWord_explain());
      myDictionary.addWord(tempWord);
    }
  }
//Present Vietnamese meaning of the word
  public void dictionaryLookup() {
    Scanner scanner = new Scanner(System.in);
    String Target = scanner.nextLine();
    Word word = dictionary.lookup(Target);
    System.out.println(word.getWord_explain());
  }

  public Word dictionaryLookup(String Target) {
    return dictionary.lookup(Target);
  }

  public void dictionarySearcher() {
    Scanner scanner = new Scanner(System.in);
    String Target = scanner.nextLine();
    ArrayList<Word> words = dictionary.searcher(Target);
    for (Word word : words) {
      System.out.print(word.getWord_target());
      System.out.println(" " + word.getWord_explain());
    }
  }

  //Return result with arraylist contained words with similar prefix.
  public ArrayList<Word> dictionarySearcher(String searchText) {
    if (searchText.equals("")) return new ArrayList<>();
    return dictionary.searcher(searchText);
  }

  public static void addWordFormCommandLine(Word wordsToAdd, Dictionary myDictionary) {
    myDictionary.addWord(wordsToAdd);
  }

  public static void editWordFormCommandLine(Word wordsToEdit, Dictionary myDictionary) {
    Word oldWord = DictionaryCommandLine.dictionarySearcher(wordsToEdit, myDictionary);
    oldWord.setWord_explain(wordsToEdit.getWord_explain());
  }

  public static void DelWordFormCommandLine(Word wordsToDel, Dictionary myDictionary) {
    wordsToDel = DictionaryCommandLine.dictionarySearcher(wordsToDel, myDictionary);
    myDictionary.getWordList().remove(wordsToDel);
  }
}
