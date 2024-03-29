package Overall;

import Overall.Dictionary;
import Overall.DictionaryCommandLine;

//import com.darkprograms.speech.translator.GoogleTranslate;
import com.darkprograms.speech.translator.GoogleTranslate;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
          "src/main/resources/Data/lingoes/save_dictionaries.txt");
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

  public static void JSonCreate(Dictionary dt) throws IOException {
    JSONArray json = new JSONArray();
    for (Word word : dt.getWordList()) {
      JSONObject jsonWord = new JSONObject();
      jsonWord.put("word", word.getWord_target());
      jsonWord.put("phonetic", word.getPhonetic());
      JSONArray jsonMeanings = new JSONArray();
      for (String type : word.getMeaning().keySet()) {
        JSONObject meaningByType = new JSONObject();

        if (!type.equals("noType") && !type.equals("noEx")) {
          meaningByType.put("partOfSpeech", type);
        } else if (type.equals("noEx")) {
          meaningByType.put("partOfSpeech", "noEx");
        } else if (type.equals("noType")) {
          meaningByType.put("partOfSpeech", "noType");
        }

        JSONArray jsonDefinitions = new JSONArray();
        for (Description des : word.getMeaning().get(type)) {
          JSONObject jsonDefinition = new JSONObject();
          jsonDefinition.put("definition", des.getDefinition());
          JSONArray jsonExamples = new JSONArray();
          for (String example : des.getExample()) {
            jsonExamples.add(example);
          }
          jsonDefinition.put("examples", jsonExamples);
          jsonDefinitions.add(jsonDefinition);
        }
        meaningByType.put("definitions", jsonDefinitions);
        jsonMeanings.add(meaningByType);
      }
//            for (Object a: hm.keySet()){
//                System.out.println(a.toString());
//            }
      jsonWord.put("meanings", jsonMeanings);
      json.add(jsonWord);
    }
    try (FileWriter file = new FileWriter("src/main/resources/Data/lingoes/emps.json")) {
      System.out.println("Write file");
      file.write(json.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void JSonCreateHistory(Dictionary dt) throws IOException {
    JSONArray json = new JSONArray();
    for (Word word : dt.getWordList()) {
      JSONObject jsonWord = new JSONObject();
      jsonWord.put("word", word.getWord_target());
      jsonWord.put("phonetic", word.getPhonetic());
      JSONArray jsonMeanings = new JSONArray();
      for (String type : word.getMeaning().keySet()) {
        JSONObject meaningByType = new JSONObject();

        if (!type.equals("noType") && !type.equals("noEx")) {
          meaningByType.put("partOfSpeech", type);
        } else if (type.equals("noEx")) {
          meaningByType.put("partOfSpeech", "noEx");
        } else if (type.equals("noType")) {
          meaningByType.put("partOfSpeech", "noType");
        }

        JSONArray jsonDefinitions = new JSONArray();
        for (Description des : word.getMeaning().get(type)) {
          JSONObject jsonDefinition = new JSONObject();
          jsonDefinition.put("definition", des.getDefinition());
          JSONArray jsonExamples = new JSONArray();
          for (String example : des.getExample()) {
            jsonExamples.add(example);
          }
          jsonDefinition.put("examples", jsonExamples);
          jsonDefinitions.add(jsonDefinition);
        }
        meaningByType.put("definitions", jsonDefinitions);
        jsonMeanings.add(meaningByType);
      }
//            for (Object a: hm.keySet()){
//                System.out.println(a.toString());
//            }
      jsonWord.put("meanings", jsonMeanings);
      json.add(jsonWord);
    }
    try (FileWriter file = new FileWriter("src/main/resources/Data/lingoes/History.json")) {
      System.out.println("Write file");
      file.write(json.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void JSonCreateFavouriteWord(Dictionary dt) throws IOException {
    JSONArray json = new JSONArray();
    for (Word word : dt.getWordList()) {
      JSONObject jsonWord = new JSONObject();
      jsonWord.put("word", word.getWord_target());
      jsonWord.put("phonetic", word.getPhonetic());
      JSONArray jsonMeanings = new JSONArray();
      for (String type : word.getMeaning().keySet()) {
        JSONObject meaningByType = new JSONObject();

        if (!type.equals("noType") && !type.equals("noEx")) {
          meaningByType.put("partOfSpeech", type);
        } else if (type.equals("noEx")) {
          meaningByType.put("partOfSpeech", "noEx");
        } else if (type.equals("noType")) {
          meaningByType.put("partOfSpeech", "noType");
        }

        JSONArray jsonDefinitions = new JSONArray();
        for (Description des : word.getMeaning().get(type)) {
          JSONObject jsonDefinition = new JSONObject();
          jsonDefinition.put("definition", des.getDefinition());
          JSONArray jsonExamples = new JSONArray();
          for (String example : des.getExample()) {
            jsonExamples.add(example);
          }
          jsonDefinition.put("examples", jsonExamples);
          jsonDefinitions.add(jsonDefinition);
        }
        meaningByType.put("definitions", jsonDefinitions);
        jsonMeanings.add(meaningByType);
      }
//            for (Object a: hm.keySet()){
//                System.out.println(a.toString());
//            }
      jsonWord.put("meanings", jsonMeanings);
      json.add(jsonWord);
    }
    try (FileWriter file = new FileWriter("src/main/resources/Data/lingoes/FavouriteWord.json")) {
      System.out.println("Write file");
      file.write(json.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void addWordsToDictionary(Word wordsToAdd, Dictionary myDictionary) {
    //myDictionary.getWordList().add(wordsToAdd);
    Word lookedUpWord = myDictionary.searchWord(wordsToAdd.getWord_target());
    if (Objects.equals(lookedUpWord.getWord_target(), "")) {
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
          "src/main/resources/Data/lingoes/E_V.txt");
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

//  public static void sortDictionary(Dictionary myDictionary) {
//    myDictionary.getWordList().sort(new Comparator<Word>() {
//      @Override
//      public int compare(Word w1, Word w2) {
//        if (w1.getWord_target().toLowerCase().charAt(0) < w2.getWord_target().toLowerCase()
//            .charAt(0)) {
//          return -1;
//        } else if (w1.getWord_target().toLowerCase().charAt(0) > w2.getWord_target().toLowerCase()
//            .charAt(0)) {
//          return 1;
//        }
//        return w1.getWord_target().toLowerCase().compareTo(w2.getWord_target().toLowerCase());
//      }
//    });
//  }

  public static void sortDictionary(Dictionary myDictionary) {
    HashSet<Word> hs = new HashSet<>();
    hs.addAll(myDictionary.getWordList());
    myDictionary.getWordList().clear();
    myDictionary.getWordList().addAll(hs);

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

  /*public void insertFromFile() throws IOException {
    String path = "C:\\Users\\Hai Anh\\Downloads\\BTL1TuDienOop-KhongCoAPI\\src\\main\\resources\\Data\\lingoes\\E_V1.html";
    File file = new File(path);
    Scanner sc = new Scanner(file, String.valueOf(StandardCharsets.UTF_8));
    double startTime = System.currentTimeMillis();
    while (sc.hasNext()){
      Word word = new Word();
      String st = sc.nextLine();
      Extract es = new Extract();
      word = es.getWordSrc(st);
      dictionary.addWord(word);
    }
//    double endTime = System.currentTimeMillis();
//    System.out.println("Duration: "+(endTime-startTime));
    sc.close();
    ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
    rd.write(dictionary.getWordList(),"C:\\Users\\Hai Anh\\Downloads\\BTL1TuDienOop-KhongCoAPI\\src\\main\\resources\\Data\\lingoes\\dictionaries.txt");
  }*/
}
