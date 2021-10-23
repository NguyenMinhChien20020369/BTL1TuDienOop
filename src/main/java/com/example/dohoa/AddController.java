package com.example.dohoa;

import Overall.Description;
import Overall.Dictionary;
import Overall.DictionaryManagement;
import Overall.Word;
import java.util.ArrayList;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddController {
  @FXML
  private TextField taget;
  @FXML
  private TextArea explain;
  @FXML
  private TextField type;
  @FXML
  private TextField phonetic;

  @FXML
  public void doMain() {
    HelloApplication.window.setScene(HelloApplication.sceneMain);
  }

  @FXML
  public void saveAdd() {
    ArrayList<Description> description = new ArrayList<>();
    String[] wordStr = explain.getText().split("\\n");
    String definition = "";
    String[] temp;
    ArrayList<String> example = new ArrayList<>();
    for (int i = 0; i < wordStr.length; i++) {
      if (Objects.equals(wordStr[i], "")) {
        description.add(new Description(definition, example));
        definition = "";
        example.clear();
        continue;
      }
      if (wordStr[i].charAt(0) == 'N') {
        temp = wordStr[i].split("Nghĩa: ");
        definition = temp[1];
      } else if (wordStr[i].charAt(0) == 'V') {
        temp = wordStr[i].split("Ví dụ: ");
        example.add(temp[1]);
      }
    }
    if (!Objects.equals(definition, "")) {
      description.add(new Description(definition, example));
    }
    Word newWord = new Word(taget.getText(), type.getText(), description, phonetic.getText());
    DictionaryManagement.addWordsToDictionary(newWord, HelloController.getDt());
    DictionaryManagement.sortDictionary(HelloController.getDt());
  }
}
