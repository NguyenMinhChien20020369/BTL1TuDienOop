package com.example.dohoa;

import Overall.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static Overall.DictionaryManagement.sortDictionary;

public class HelloController {

  //    @FXML
//    private Label welcomeText;
  @FXML
  private TextArea txt;
  @FXML
  private TextArea textarea;
  @FXML
  private Button btn;
  @FXML
  private VBox list_word;
  @FXML
  private ScrollPane scrollPane1;

  @FXML
  private TextField textField;
  @FXML
  private Label wordLabel;

  public void initialize(URL url, ResourceBundle rb) {

  }
  Dictionary dt = new Dictionary();
  ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
  //
//  @FXML
//  public void doSearch() {
//    System.out.println("Here we are ");
//    String word = txt.getText();
//    Dictionary myDictionary = new Dictionary();
//    DictionaryCommandLine dtr = new DictionaryCommandLine();
//    dtr.dictionaryAdvanced(myDictionary);
//    sortDictionary(myDictionary);
//
//    int expl = myDictionary.searchWord(word);
//
//    String a = myDictionary.lookup(word).getWord_explain();
//    if (a.equalsIgnoreCase("erro")) {
//      textarea.setText("something wrong");
//    } else {
//      textarea.setText(a);
//    }
//  }

//  public void initialize(URL url, ResourceBundle resourceBundle) {
//    try {
//      dictionaryManagement.insertFromFileJSON();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    textarea.setEditable(false);
//  }

  public void doSearch(String inputStr){
    Word word = dt.searchWord(inputStr);
//        System.out.println(word.getWord_target());
    String wordStr = "";
    for (String a: word.getMeaning().keySet()){
      if (!a.equals("noType") && !a.equals("noEx"))   wordStr+=a+"\n\n";
      for (Description b : word.getMeaning().get(a)){
        wordStr+= b.getDefinition()+"\n";
        for (String c: b.getExample()){
          wordStr+=c+"\n";
        }
      }
    }
    textarea.setText(wordStr);
    wordLabel.setText(word.getWord_target());
//    wordLabel.setFont(Font.font(24));
//    wordLabel.setTextFill(Color.BLUE);
  }
  void addClickListener(Label label){
    label.setOnMouseClicked(mouseEvent -> {
      String labelStr = label.getText();
      System.out.println(labelStr);
      doSearch(labelStr);
    });
  }

  public void displayWord(){
    dt.setWords(rd.read());
    textarea.setEditable(false);
    list_word.getChildren().clear();
    if(!textField.getText().isEmpty()) {
      String inputStr = textField.getText();
      ArrayList<Word> displayWord = dt.advancedSearchWord(inputStr);
      System.out.println(displayWord.size());
      for (Word a : displayWord) {
        Label word = new Label(a.getWord_target());
        addClickListener(word);
        //word.setMinWidth(270);
        //word.setMinHeight(45);
        //word.setStyle("-fx-background-color: #E1E1E1");
        //word.setStyle("-fx-text-alignment: left");
        //word.setPadding(new Insets(0, 0, 10, 5));
        //word.setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.THICK)));
        list_word.getChildren().add(word);
        //scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //scrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
      }
      scrollPane1.setContent(list_word);
    }
  }

//  @FXML
//  public void doAPI() {
//    String word = DictionaryManagement.API(txt.getText());
//    System.out.println(word);
//    textarea.setText(word);
//  }

//    public static class InternetConnection {
//        public String getOnlineData(String word)
//        {
//            String data = "";
//            String deCodeData = "";
//            try {
//                URL url = new URL("https://api.dictionaryapi.dev/api/v1/entries/en/"+word);
//                HttpURLConnection con =(HttpURLConnection) url.openConnection();
//                if(con.getResponseCode() == 200){
//                    InputStream im = con.getInputStream();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(im));
//                    String line  = br.readLine();
//                    while(line != null)
//                    {
//                        data= data+line;
//                        line = br.readLine();
//                    }
//                    br.close();
//                    JSONDecoder jd = new JSONDecoder();
//                    deCodeData = jd.Decoder(data);
//                }else{
//                    deCodeData = "erro";
//                    //System.out.println("Internet connection erro");
//                }
//            }catch (Exception e) {
//                try {
//                    deCodeData = "erro";
//                    System.out.println(e);
//                } catch (Exception exception) {
//                    System.out.println(exception);
//                }
//            }
//            return deCodeData ;
//        }
//    }
//
//    public static class JSONDecoder {
//        public String Decoder(String data){
//            String deData = "";
//            try {
//                Object obj = JSONValue.parse(data);
//
//                JSONArray ja = (JSONArray) obj;
//
//                JSONObject jo = (JSONObject) ja.get(0);
//
//                deData = deData + "Overall.Word:- " + jo.get("word");
//
//                JSONObject jo1 = (JSONObject) jo.get("meaning");
//                JSONArray ja1 = (JSONArray) jo1.get("noun");
//                JSONObject jo2 = (JSONObject) ja1.get(0);
//                deData = deData + "\n PartOfSpeech:- Noun";
//                deData = deData + "\n Definition:- " + jo2.get("definition");
//                deData = deData + "\n Example:- " + jo2.get("example");
//
//                JSONArray ja3 = (JSONArray) jo2.get("synonyms");
//                if (!(ja3 == null)) {
//                    deData = deData + "\nSynonyms";
//
//                    for (int i = 0; i < ja3.size(); i++) {
//                        deData = deData + "\n \t" + ja3.get(i);
//                    }
//                }
//            } catch (Exception e) {
//                deData = "";
//            }
//            return deData;
//        }
//
//    }
}