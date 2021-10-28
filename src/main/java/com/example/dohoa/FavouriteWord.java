package com.example.dohoa;

import Overall.ReadFileWithBufferedReader;
import Overall.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FavouriteWord implements Initializable  {

    @FXML
    private TextArea textarea;
    @FXML
    private VBox listword;
    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private TextField phonetic;
    //    @FXML
//    private TextField target;
    //private TextToSpeech TTS = new TextToSpeech();
    private ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
    private Word presentWord;

//    public FavouriteWord(TextArea textarea, VBox listword, ScrollPane scrollPane1, TextField phonetic) {
//        this.textarea = textarea;
//        this.listword = listword;
//        this.scrollPane1 = scrollPane1;
//        this.phonetic = phonetic;
//    }

    public void ShowFavouriteWord(){
        for( Word word :HelloController.getDtFavouriteWord().getWordList()){
            Label word1 = new Label(word.getWord_target());
            addClickListener(word1);
            this.listword.getChildren().add(word1);
            //scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            //scrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        }
        scrollPane1.setContent(listword);
    }
    public void doSearch(String inputStr) {

        presentWord = HelloController.getDtFavouriteWord().searchWord(inputStr);
        // WordHistory.add(presentWord);

//        System.out.println(word.getWord_target());
        String wordStr = "";
        for (String a : presentWord.getMeaning().keySet()) {
            if (!a.equals("noType") && !a.equals("noEx")) {
                wordStr += "Loại: " + a + "\n\n";
            } else {
                wordStr += "Loại: chưa có" + "\n\n";
            }
            for (int i = 0; i < presentWord.getMeaning().get(a).size(); i++) {
                if (!Objects.equals(presentWord.getMeaning().get(a).get(i).getDefinition(), "")) {
                    wordStr += "Nghĩa: " + presentWord.getMeaning().get(a).get(i).getDefinition() + "\n";
                    for (String c : presentWord.getMeaning().get(a).get(i).getExample()) {
                        wordStr += "Ví dụ: " + c + "\n";
                    }
                    wordStr += "\n";
                }
            }
        }
        textarea.setText(wordStr);
        //target.setText(presentWord.getWord_target());
        phonetic.setText(presentWord.getPhonetic());
//    wordLabel.setFont(Font.font(24));
//    wordLabel.setTextFill(Color.BLUE);
    }

    void addClickListener(Label label) {
        label.setOnMouseClicked(mouseEvent -> {
            String labelStr = label.getText();
            System.out.println(labelStr);
            doSearch(labelStr);
        });
    }
    @FXML
    public void doMain() {
        HelloApplication.window.setScene(HelloApplication.sceneMain);
    }
    @FXML
    public void changeSceneHistorySearching() throws IOException {
        HelloController.SceneHistorySearching();
    }
    @FXML
    public void changeSceneFavouriteWord() throws IOException {
        HelloController.SceneFavouriteWord();
    }
    @FXML
    public  void ChangeAdd(){
        HelloController.SceneAdd();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.ShowFavouriteWord();
    }
}
