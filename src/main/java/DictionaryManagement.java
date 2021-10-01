import java.util.Scanner;

public class DictionaryManagement {

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
