import org.junit.Assert;
import org.junit.Test;

public class DictionaryTest {
  @Test
  public void binaryLookupTest() {
    Dictionary dictionary = new Dictionary();
    DictionaryManagement.addWordsToDictionary(new Word("Hello", "Xin chao"), dictionary);
    DictionaryManagement.addWordsToDictionary(new Word("House", "Ngoi nha"), dictionary);
    Assert.assertEquals("Ngoi nha", dictionary.lookup("House").getWord_explain());
  }
}
