/*import java.io.IOException;
import Overall.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//import static Overall.DictionaryManagement.dictionary;
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
//  public  static void dictionaryAdvanced(Dictionary myDictionary) {
////    ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
////    ArrayList<Word> arr = rd.read();
////    for(int i = 0 ; i < arr.size();i++){
////      myDictionary.addWord(arr.get(i));
////    }
//   // showAllWords(myDictionary);
//    DictionaryManagement.insertFromFile(myDictionary);
//    //showAllWords(myDictionary);
//  }

  public static void main(String[] args) throws IOException {
    // Scanner sc = new Scanner(System.in);
//    Dictionary myDictionary = new Dictionary();
//    ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
//    myDictionary.setWords(rd.read());
//    //dictionaryAdvanced(myDictionary);
//     String word = "élitism";
//    sortDictionary(myDictionary);
//    Word wd = myDictionary.binaryLookup(0,myDictionary.getWordList().size(),word);
//    System.out.println(wd.getPhonetic());
    //DictionaryManagement.dictionaryExportToFile(myDictionary);
    //Tạo file txt từ html
    CreateJsonFile CJF = new CreateJsonFile();
    CJF.JSonCreate();
    //Kết thúc tạo file
  }
}
*/