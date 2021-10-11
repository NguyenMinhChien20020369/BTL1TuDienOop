import java.util.ArrayList;
import java.util.Locale;

public class Dictionary {

  private ArrayList<Word> wordList;
//  public void push(Word word) {
//    int length = wordList.size();
//    int index = searchIndexInsert(0, length - 1, word.getWord_target());
//    if (index <= length && index >= 0) wordList.add(index, word);
//  }
//
//  private int searchIndexInsert(int start, int end, String Target) {
//    if (end < start) return start;
//    int mid = start + (end - start) / 2;
//    if (mid == wordList.size()) return mid;
//    Word word = wordList.get(mid);
//    int compare = word.getWord_explain().compareTo(Target);
//    if (compare == 0) return -1;
//    if (compare > 0) return searchIndexInsert(start, mid - 1, Target);
//    return searchIndexInsert(mid + 1, end, Target);
//  }

  //Find the word in the dictionary
  private Word binaryLookup(int start, int end, String Target) {
    if (end < start) {
      return null;
    }
    int mid = start + (end - start) / 2;
    Word word = wordList.get(mid);
    String currentTarget = word.getWord_target();
    if (currentTarget.toLowerCase().charAt(0) < Target.toLowerCase().charAt(0)) {
      return binaryLookup(mid + 1, end, Target);
    } else if (currentTarget.toLowerCase().charAt(0) > Target.toLowerCase().charAt(0)) {
      return binaryLookup(start, mid - 1, Target);
    } else {
      int compare = currentTarget.toLowerCase().compareTo(Target.toLowerCase());
      if (compare == 0) {
        return word;
      }
      if (compare > 0) {
        return binaryLookup(start, mid - 1, Target);
      }
      return binaryLookup(mid + 1, end, Target);
    }
  }

  //Find the index of the word in the dictionary
  private int binarySearcher(int start, int end, String Target) {
    if (end < start) {
      return -1;
    }
    int mid = start + (end - start) / 2;
    Word word = wordList.get(mid);
    String currentTarget = word.getWord_target();
    if (currentTarget.startsWith(Target)) {
      return mid;
    }
    int compare = currentTarget.compareTo(Target);
    if (compare == 0) {
      return mid;
    }
    if (compare > 0) {
      return binarySearcher(start, mid - 1, Target);
    }
    return binarySearcher(mid + 1, end, Target);
  }

  //Return the finding word
  public Word lookup(String Target) {
    return binaryLookup(0, wordList.size() - 1, Target);
  }

  //Find simillar Target in the source
  public ArrayList<Word> searcher(String Target) {
    ArrayList<Word> result = new ArrayList<>();
    int index = binarySearcher(0, wordList.size() - 1, Target);
    if (index >= 0) {
      result.add(wordList.get(index));
      int left = index - 1, right = index + 1;
      while (left >= 0) {
        Word leftWord = wordList.get(left--);
        if (leftWord.getWord_target().startsWith(Target)) {
          result.add(leftWord);
        } else {
          break;
        }
      }

      int length = wordList.size();
      while (right < length) {
        Word RightWord = wordList.get(right++);
        if (RightWord.getWord_target().startsWith(Target)) {
          result.add(RightWord);
        } else {
          break;
        }
      }
    }
    return result;
  }

  Dictionary() {
    wordList = new ArrayList<>();
  }

  public void addWord(Word tempWord) {
    this.wordList.add(tempWord);
  }

  public ArrayList<Word> getWordList() {
    return this.wordList;
  }

  public void setWords(ArrayList<Word> words) {
    this.wordList = words;
  }
}