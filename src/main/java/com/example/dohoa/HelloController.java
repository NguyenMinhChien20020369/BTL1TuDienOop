package com.example.dohoa;

import Overall.*;

import java.util.*;

import Overall.Dictionary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;

import static Overall.DictionaryManagement.sortDictionary;

public class HelloController implements Initializable {

  //    @FXML
//    private Label welcomeText;
  @FXML
  private TextArea txt;
  @FXML
  private TextArea textarea;

  @FXML
  private VBox list_word;
  @FXML
  private ScrollPane scrollPane1;
  @FXML
  private TextField phonetic;
  @FXML
  private TextField target;
  private static DictionaryManagement DTM = new DictionaryManagement();
  private TextToSpeech TTS = new TextToSpeech();
  private static Dictionary dt = new Dictionary();
  private static Dictionary DtHistory = new Dictionary();
  private static Dictionary DtFavouriteWord = new Dictionary();
  private ReadFileWithBufferedReader rd = new ReadFileWithBufferedReader();
  private Word presentWord;
  //private HistorySearching HS = new HistorySearching();

  public static Dictionary getDtFavouriteWord() {
    return DtFavouriteWord;
  }

  public static Dictionary getDt() {
    return dt;
  }

  public static Dictionary GetDtHistory() {
    return DtHistory;
  }

  private String EnglishWord = new String();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    dt.setWords(rd.read());
    DtHistory.setWords(rd.readHistory());
    DtFavouriteWord.setWords(rd.readFavouriteWord());
    //textarea.setEditable(false);
  }

  @FXML
  public void Speak() {
    TTS.SpeakTheWord(this.EnglishWord);
  }

  public void DeleteDuplicate(ArrayList<Word> arr) {
    HashSet<Word> hs = new HashSet<>();
    hs.addAll(arr);
    arr.clear();
    arr.addAll(hs);
  }

  public void doSearch(String inputStr) throws IOException {
    presentWord = dt.searchWord(inputStr);
    DtHistory.getWordList().add(0, presentWord);
    //DeleteDuplicate(DtHistory.getWordList());

//    HS.getWordHistory().add(presentWord);
//    if(HS.getWordHistory().size() >= 50) HS.getWordHistory().clear();
//    System.out.println(HS.getWordHistory().size());

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
    target.setText(presentWord.getWord_target());
    phonetic.setText(presentWord.getPhonetic());
    list_word.getChildren().remove(0, list_word.getChildren().size());
    scrollPane1.setContent(list_word);
//    wordLabel.setFont(Font.font(24));
//    wordLabel.setTextFill(Color.BLUE);
  }

  void addClickListener(Label label) {
    label.setOnMouseClicked(mouseEvent -> {
      String labelStr = label.getText();
      this.EnglishWord = labelStr;
      System.out.println(labelStr);
      try {
        doSearch(labelStr);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public void displayWord() {
    if (!txt.getText().isEmpty()) {
      String inputStr = txt.getText();
      ArrayList<Word> displayWord = dt.advancedSearchWord(inputStr);
      if (displayWord.size() == 0) {
        textarea.setText("Hay nhap tu khac vao o search, tu cua ban khong ton tai trong tu dien");
        return;
      }
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

  @FXML
  public void doAPI() {
    String word = DictionaryManagement.API(txt.getText());
    System.out.println(word);
    textarea.setText(word);
  }

  @FXML
  public void editWord() {
    String[] wordStr = textarea.getText().split("\\n");
    String[] temp = wordStr[0].split("Loại: ");
    String type = temp[1];
    String word_target = target.getText();
    ArrayList<Description> description = new ArrayList<>();
    String definition = "";
    ArrayList<String> example = new ArrayList<>();
    for (int i = 2; i < wordStr.length; i++) {
      if (Objects.equals(wordStr[i], "")) {
        description.add(new Description(definition, example));
        definition = "";
        example.clear();
        continue;
      }
      if (wordStr[i].charAt(0) == 'N') {
        /*if (!Objects.equals(definition, "")) {
          description.add(new Description(definition, example));
          definition = "";
          example.clear();
        }*/
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
    Word newWord = new Word(word_target, type, description, phonetic.getText());
    dt.getWordList().remove(presentWord);
    presentWord = null;
    DictionaryManagement.addWordsToDictionary(newWord, dt);
    DictionaryManagement.sortDictionary(dt);
    //presentWord
  }

  @FXML
  public void deleteWord() {
    dt.getWordList().remove(presentWord);
    presentWord = null;
  }

  public void FavouriteWord() throws IOException {
    DtFavouriteWord.getWordList().add(0, presentWord);
    DeleteDuplicate(DtFavouriteWord.getWordList());

  }

  @FXML
  public void changeSceneAdd() {
    HelloApplication.window.setScene(HelloApplication.sceneAdd);
  }

  @FXML
  public void changeSceneHistorySearching() throws IOException {
    FXMLLoader fxmlLoaderHistory = new FXMLLoader(
        HelloApplication.class.getResource("HistorySearching.fxml"));
    try {
      HelloApplication.sceneHistorySearching = new Scene(fxmlLoaderHistory.load(), 1212, 769);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //DTM.JSonCreateHistory(GetDtHistory());
    HelloApplication.window.setScene(HelloApplication.sceneHistorySearching);
  }

  @FXML
  public void changeSceneFavouriteWord() throws IOException {
    FXMLLoader fxmlFavouriteWord = new FXMLLoader(
        HelloApplication.class.getResource("FavouriteWord.fxml"));
    try {
      HelloApplication.sceneFavouriteWord = new Scene(fxmlFavouriteWord.load(), 1212, 769);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //DTM.JSonCreateFavouriteWord(getDtFavouriteWord());
    HelloApplication.window.setScene(HelloApplication.sceneFavouriteWord);
  }


  @FXML
  public static void SceneAdd() {
    HelloApplication.window.setScene(HelloApplication.sceneAdd);
  }

  @FXML
  public static void SceneHistorySearching() throws IOException {
    FXMLLoader fxmlLoaderHistory = new FXMLLoader(
        HelloApplication.class.getResource("HistorySearching.fxml"));
    try {
      HelloApplication.sceneHistorySearching = new Scene(fxmlLoaderHistory.load(), 1212, 769);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //DTM.JSonCreateHistory(GetDtHistory());
    HelloApplication.window.setScene(HelloApplication.sceneHistorySearching);
  }

  @FXML
  public static void SceneFavouriteWord() throws IOException {
    FXMLLoader fxmlFavouriteWord = new FXMLLoader(
        HelloApplication.class.getResource("FavouriteWord.fxml"));
    try {
      HelloApplication.sceneFavouriteWord = new Scene(fxmlFavouriteWord.load(), 1212, 769);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //DTM.JSonCreateFavouriteWord(getDtFavouriteWord());
    HelloApplication.window.setScene(HelloApplication.sceneFavouriteWord);
  }
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




