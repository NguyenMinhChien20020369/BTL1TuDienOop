import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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
    if (searchText.equals("")) {
      return new ArrayList<Word>();
    }
    return dictionary.searcher(searchText);
  }

  public static void addWordsToDictionary(Word wordsToAdd, Dictionary myDictionary) {
    Word lookedUpWord = myDictionary.lookup(wordsToAdd.getWord_target());
    if (lookedUpWord == null) {
      myDictionary.getWordList().add(wordsToAdd);
      return;
    }
    String tempWord_explain = "";
    for (int i = 0; i < lookedUpWord.getWord_explain().length(); i++) {
      if (lookedUpWord.getWord_explain().charAt(i) == ' ') {
        tempWord_explain = "";
      }
      if (lookedUpWord.getWord_explain().charAt(i) != ','
          && lookedUpWord.getWord_explain().charAt(i) != ';') {
        tempWord_explain = tempWord_explain.concat(
            Character.toString(lookedUpWord.getWord_explain().charAt(i)));
        if (i == lookedUpWord.getWord_explain().length() - 1) {
          tempWord_explain = tempWord_explain.trim();
          if (tempWord_explain.equals(wordsToAdd.getWord_explain())) {
            return;
          }
        }
      } else {
        tempWord_explain = tempWord_explain.trim();
        if (tempWord_explain.equals(wordsToAdd.getWord_explain())) {
          return;
        }
      }
    }
    lookedUpWord.setWord_explain(
        lookedUpWord.getWord_explain() + ", " + wordsToAdd.getWord_explain());
  }

  public static void editWordsInTheDictionary(Word correctedWord, Dictionary myDictionary) {
    Word oldWord = myDictionary.lookup(correctedWord.getWord_target());
    oldWord.setWord_explain(correctedWord.getWord_explain());
  }

  public static void deleteWordFromDictionary(String target, Dictionary myDictionary) {
    myDictionary.getWordList().remove(myDictionary.lookup(target));
  }

  public static void insertFromFile(Dictionary myDictionary) {
    try {
      File f = new File("D:/chuanMuc/BTL1TuDienOop/dictionaries.txt");
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        String tempWord_target = "";
        String tempWord_explain = "";
        boolean takeExplain = false;
        for (int i = 0; i < line.length(); i++) {
          if (takeExplain) {
            tempWord_explain = tempWord_explain.concat(Character.toString(line.charAt(i)));
            continue;
          }
          if (line.charAt(i) != '\t') {
            tempWord_target = tempWord_target.concat(Character.toString(line.charAt(i)));
          } else {
            takeExplain = true;
          }
        }
        addWordsToDictionary(new Word(tempWord_target.trim(), tempWord_explain.trim()),
            myDictionary);
      }
      fr.close();
      br.close();
    } catch (Exception ex) {
      System.out.println("File read error: " + ex);
    }
  }

  public static void dictionaryExportToFile(Dictionary myDictionary) {
    try {
      File f = new File("D:/chuanMuc/BTL1TuDienOop/dictionaries.txt");
      FileWriter fw = new FileWriter(f);
      for (int i = 0; i < myDictionary.getWordList().size(); i++) {
        fw.write(myDictionary.getWordList().get(i).getWord_target() + '\t');
        fw.write(myDictionary.getWordList().get(i).getWord_explain() + '\n');
      }
      fw.close();
    } catch (IOException ex) {
      System.out.println("File write error: " + ex);
    }
  }
}
