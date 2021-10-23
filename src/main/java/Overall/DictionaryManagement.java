package Overall;

import Overall.Dictionary;
import Overall.DictionaryCommandLine;

//import com.darkprograms.speech.translator.GoogleTranslate;
import com.darkprograms.speech.translator.GoogleTranslate;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class DictionaryManagement {

  protected static Dictionary dictionary = new Dictionary();

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
      return new ArrayList<>();
    }
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

  public void saveWordsToFile() throws IOException {
    ReadFileWithBufferedReader read = new ReadFileWithBufferedReader();
    read.write(dictionary.getWordList(), "data/dictionaries.txt");
  }

  public void addWord(Word word) throws IOException {
    dictionary.push(word);
    this.saveWordsToFile();
  }

  public static void editWordsInTheDictionary(Word correctedWord, Dictionary myDictionary) {
    Word oldWord = myDictionary.lookup(correctedWord.getWord_target());
    oldWord.setWord_explain(correctedWord.getWord_explain());
  }

  public static void deleteWordFromDictionary(String target, Dictionary myDictionary) {
    myDictionary.getWordList().remove(myDictionary.lookup(target));
  }

  public void dictionaryExportToFile() throws IOException {
    ReadFileWithBufferedReader read = new ReadFileWithBufferedReader();
    read.write(dictionary.getWordList());
  }

  public static void dictionaryExportToFile(Dictionary myDictionary) {
    try {
      File f = new File(
          "D:\\chuanMuc\\BTL1TuDienOop\\src\\main\\resources\\Data\\lingoes\\save_dictionaries.txt");
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

  public static void addWordsToDictionary(Word wordsToAdd, Dictionary myDictionary) {
    //myDictionary.getWordList().add(wordsToAdd);
    Word lookedUpWord = myDictionary.lookup(wordsToAdd.getWord_target());
    if (lookedUpWord == null) {
      myDictionary.getWordList().add(wordsToAdd);
    }
    /*String tempWord_explain = "";
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
        lookedUpWord.getWord_explain() + ", " + wordsToAdd.getWord_explain());*/
  }

  public static void insertFromFile(Dictionary myDictionary) {
    try {
      File f = new File(
          "D:\\chuanMuc\\BTL1TuDienOop\\src\\main\\resources\\Data\\lingoes\\E_V.txt");
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
        addWordsToDictionary(new Word(tempWord_target, tempWord_explain), myDictionary);
      }
      fr.close();
      br.close();
    } catch (Exception ex) {
      System.out.println("File read error: " + ex);
    }
  }

  public static void sortDictionary(Dictionary myDictionary) {
    myDictionary.getWordList().sort(new Comparator<Word>() {
      @Override
      public int compare(Word w1, Word w2) {
        int index1 = 0;
        int index2 = 0;
        if (w1.getWord_target().charAt(0) == '-') {
          index1++;
        }
        if (w2.getWord_target().charAt(0) == '-') {
          index2++;
        }
        if (w1.getWord_target().toLowerCase().charAt(index1) < w2.getWord_target().toLowerCase()
            .charAt(index2)) {
          return -1;
        } else if (w1.getWord_target().toLowerCase().charAt(index1) > w2.getWord_target().toLowerCase()
            .charAt(index2)) {
          return 1;
        }
        return w1.getWord_target().toLowerCase().compareTo(w2.getWord_target().toLowerCase());
      }
    });
  }

  public static String API(String text) {
    String[] txt = text.split("\\n");
    String word = "";
    try {
      for (String i : txt) {
        if (Objects.equals(i, "")) {
          word += "\n";
          continue;
        }
        String[] words = i.split("\\.");
        for (String j : words) {
         word += GoogleTranslate.translate("en","vi", j) + ". ";
        }
        word += "\n";
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return word;
  }
}
