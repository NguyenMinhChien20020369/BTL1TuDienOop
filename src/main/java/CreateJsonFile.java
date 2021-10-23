/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import Overall.*;

public class CreateJsonFile {
//[{"phonetic":null,"word":"-mancy","meanings":[{"partOfSpeech":"noEx","definitions":[{"examples":[],"definition":"hình thái ghép chỉ sự đoán trước mà đã đoán"}]}]
//        {"phonetic":null,"word":"-trophin","meanings":[{"partOfSpeech":"noType","definitions":[{"examples":["gonadotrophin hocmon: kích thích tuyến sinh dục"],"definition":"chất kích thích"}]},{"partOfSpeech":"noEx","definitions":[{"examples":[],"definition":"hình thái ghép có nghĩa"}]}]}


  //  public static void main(String[] args) throws IOException {
//            DictionaryManagement dictionaryManagement = new DictionaryManagement();
//            dictionaryManagement.insertFromFile();
  public void JSonCreate() throws IOException {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    dictionaryManagement.insertFromFile();
    Dictionary dt = dictionaryManagement.getDictionary();
    JSONArray json = new JSONArray();
    for (Word word : dt.getWordList()) {
      JSONObject jsonWord = new JSONObject();
      jsonWord.put("word", word.getWord_target());
      jsonWord.put("phonetic", word.getPhonetics());
      JSONArray jsonMeanings = new JSONArray();
      for (String type : word.getMeaning().keySet()) {
        JSONObject meaningByType = new JSONObject();

        if (!type.equals("noType") && !type.equals("noEx")) {
          meaningByType.put("partOfSpeech", type);
        } else if (type.equals("noEx")) {
            meaningByType.put("partOfSpeech", "noEx");
        } else if (type.equals("noType")) {
            meaningByType.put("partOfSpeech", "noType");
        }

        JSONArray jsonDefinitions = new JSONArray();
        for (Description des : word.getMeaning().get(type)) {
          JSONObject jsonDefinition = new JSONObject();
          jsonDefinition.put("definition", des.getDefinition());
          JSONArray jsonExamples = new JSONArray();
          for (String example : des.getExample()) {
            jsonExamples.add(example);
          }
          jsonDefinition.put("examples", jsonExamples);
          jsonDefinitions.add(jsonDefinition);
        }
        meaningByType.put("definitions", jsonDefinitions);
        jsonMeanings.add(meaningByType);
      }
//            for (Object a: hm.keySet()){
//                System.out.println(a.toString());
//            }
      jsonWord.put("meanings", jsonMeanings);
      json.add(jsonWord);
    }
    try (FileWriter file = new FileWriter("EMPS1.json")) {
      System.out.println("Write file");
      System.out.println(json.toJSONString());
      file.write(json.toJSONString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

//        FileReader fileReader = new FileReader("emps.json");
//        BufferedReader bf = new BufferedReader(fileReader);
//        JSONDecoder jsonDecoder = new JSONDecoder();
//        String wordStr = bf.readLine();
//       // System.out.println(wordStr);
//        Word wordInput =  jsonDecoder.Decoder(wordStr);
//        wordInput.displayWord();
  }
//    }
}
*/