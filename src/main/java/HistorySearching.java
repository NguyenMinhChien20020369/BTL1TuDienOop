import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HistorySearching extends DictionaryPractice{
    public void InsertFromFile() {
        System.out.println("Start insert history from file.");
        ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
        ArrayList<Word> adds = rd.read("src/main/resources/History/History.txt");
        Collections.sort(adds, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareTo(o2.getWord_target());
            }
        });
        dictionary.setWords(adds);
        System.out.println("End insert history from file.");
    }

    public void SaveWordsToFile() throws IOException {
        ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
        rd.write(dictionary.getWordList(), "src/main/resources/History/History.txt");
    }
}
