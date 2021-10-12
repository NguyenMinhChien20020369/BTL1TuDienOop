package Overall;

public class Word {

  private String word_target;
  private String word_explain;

  public Word() {
    this("", "");
  }

  public Word(String Target, String Explain) {
    this.word_target = Target.trim();
    this.word_explain = Explain.trim();
  }

  public String getWord_target() {
    return this.word_target;
  }

  public void setWord_target(String tempWord_target) {
    this.word_target = tempWord_target.trim();
  }

  public String getWord_explain() {
    return this.word_explain;
  }

  public void setWord_explain(String tempWord_explain) {
    this.word_explain = tempWord_explain.trim();
  }


}
