package Services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.ParseException;
import java.util.Iterator;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Json {
    public static void main(String[] args) {

        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", 100);

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);

        try (FileWriter file = new FileWriter("C:\\GitHub\\BTL1TuDienOop\\test.json")) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(obj);

        JSONParser parser = new JSONParser();
        try (
    Reader reader = new FileReader("C:\\GitHub\\BTL1TuDienOop\\test.json")) {

        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        System.out.println();

        String name = (String) jsonObject.get("name");
        System.out.println(name);

        long age = (Long) jsonObject.get("age");
        System.out.println(age);

        // loop array
        JSONArray msg = (JSONArray) jsonObject.get("messages");
        Iterator<String> iterator = msg.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    } catch (IOException e) {
        e.printStackTrace();
    } catch (
                org.json.simple.parser.ParseException e) {
        e.printStackTrace();
    }

}

}
