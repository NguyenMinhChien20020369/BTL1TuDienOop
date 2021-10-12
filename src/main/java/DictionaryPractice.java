import java.util.ArrayList;

public class DictionaryPractice extends DictionaryManagement{
        public void showSearcher(String Target) {
            ArrayList<Word> words = this.dictionarySearcher(Target);
            for (Word word : words) {
                System.out.println(word.getWord_target());
            }
        }

        public ArrayList<String> getStringSearchs(String Target) {
            ArrayList<Word> words = this.dictionarySearcher(Target);
            ArrayList<String> result = new ArrayList<>();

            for (Word word : words) {
                result.add(word.getWord_target());
            }
            return result;
        }
}
