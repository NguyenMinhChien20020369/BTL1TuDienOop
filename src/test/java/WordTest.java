import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordTest {
  @Test
  public void TestParameterlessConstructor() {
    Word word = new Word();
    assertEquals("",word.getWord_target());
    assertEquals("",word.getWord_explain());
  }
}
