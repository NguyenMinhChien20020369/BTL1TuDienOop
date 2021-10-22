package Overall;

import Overall.Description;
import Overall.Word;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class JSONDecoder {
    void addMeaningByType(Word word, JSONObject jo1){
            JSONArray noun = (JSONArray) jo1.get("definitions");
            //System.out.println(checkType);
            String type = (String) jo1.get("partOfSpeech");
            ArrayList<Description> meaning = new ArrayList<>();
            for (int i = 0; i < noun.size(); i++) {
                Description des = new Description();
                JSONObject def = (JSONObject) noun.get(i);
                des.setDefinition(def.get("definition").toString());
                //TODO : MAKE EXAMPLE AS ARRAY
                if (def.get("examples") != null) {
                    JSONArray examples = (JSONArray) def.get("examples");
                    for (Object ex :examples) {
                        des.setExample(ex.toString());
                    }
                }
                meaning.add(des);
            }
            word.addMeaning(type, meaning);
    }
    public  ArrayList<Word> Decoder(String data){
        ArrayList<Word> words = new ArrayList<>();
        try {

            //Get word from Json string
            Object obj = JSONValue.parse(data);
            JSONArray ja = (JSONArray) obj;
            for (int i = 0; i < ja.size(); i++) {
                Word word = new Word();
                JSONObject jo = (JSONObject) ja.get(i);
                word.setWord_target((String) jo.get("word"));


                //Get the meaning source
                JSONArray JsonMeaning = (JSONArray) jo.get("meanings");
                for (int k = 0; k < JsonMeaning.size(); k++) {
                    JSONObject jo1 = (JSONObject) JsonMeaning.get(k);
                    // System.out.println(jo1);
                    // System.out.println(type);
                    addMeaningByType(word, jo1);
                }
                words.add(word);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return words;
    }
}
