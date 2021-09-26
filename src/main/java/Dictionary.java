import java.util.ArrayList;
import java.util.List;

public class Dictionary {

  private List<Word> wordList;

  Dictionary() {
    wordList = new ArrayList<>();
  }

  public void addWord(Word tempWord) {
    this.wordList.add(tempWord);
  }

  public List<Word> getWordList() {
    return this.wordList;
  }
}