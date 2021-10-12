package com.example.dohoa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import Overall.DictionaryManagement;
import Overall.Dictionary;
import Overall.ReadFileWithBufferedReader;
import Overall.Word;
import Overall.DictionaryPractice;
import Overall.HistorySearching;
import Overall.DictionaryCommandLine;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import static Overall.DictionaryManagement.sortDictionary;

public class HelloController   {
//    @FXML
//    private Label welcomeText;
    @FXML
    private TextField txt;
    @FXML
    private TextArea textarea;
    @FXML
    private Button btn;

  public void initialize(URL url, ResourceBundle rb)
    {

     }
//
    @FXML
    public void doSearch() {
        System.out.println("Here we are ");
        String word = txt.getText();
        Scanner sc = new Scanner(System.in);
        Dictionary myDictionary = new Dictionary();
        DictionaryCommandLine dtr = new DictionaryCommandLine();
        dtr.dictionaryAdvanced(myDictionary);
        sortDictionary(myDictionary);

        int expl = myDictionary.searchWord(myDictionary,word);

        String a = myDictionary.lookup(word).getWord_explain();
        if(a.equalsIgnoreCase("erro")) textarea.setText("something wrong");
        textarea.setText(a);
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