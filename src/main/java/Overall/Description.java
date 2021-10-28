package Overall;

import java.util.ArrayList;
import java.util.HashMap;

public class Description {

  private String definition;
  private ArrayList<String> example;

  public Description() {
    definition = new String();
    example = new ArrayList<>();
  }

  public Description(String definition) {
    this.definition = definition;
    example = new ArrayList<>();
  }

  public Description(String definition, ArrayList<String> example) {
    this.definition = definition;
    this.example = new ArrayList<>(example);
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public void setExample(String example) {
    this.example.add(example);
  }

  public String getDefinition() {
    return definition;
  }

  public ArrayList<String> getExample() {
    return example;
  }
}
