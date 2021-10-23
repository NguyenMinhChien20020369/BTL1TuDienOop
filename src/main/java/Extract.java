//package Overall;
//
//import java.util.ArrayList;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Scanner;
//abandons<html><i>abandon /ə'bændən/</i><br/><ul><li><b><i> ngoại động từ</i></b><ul><li><font color='#cc0000'><b> bộm (nhiếp ảnh) (nhiếp ảnh) (từ mỹ,nghĩa mỹ) từ bỏ; bỏ rơi, ruồng bỏ</b></font><ul><li><b>to abandon a hope</b>: từ bỏ hy vọng</li><li><b>to abandon one's wife and children</b>: ruồng bỏ vợ con</li><li><b>to abandon oneself to</b>: đắm đuối, chìm đắm vào (nỗi thất vọng...)</li></li></ul></ul></li></ul><ul><li><b><i> danh từ</i></b><ul><li><font color='#cc0000'><b> sự phóng túng, sự tự do, sự buông thả</b></font><ul><li><b>with abandon</b>: phóng túng</li></ul></li></ul></li></ul></html>
//public class Extract {
//    public  Word getWordSrc(String html){
//        Word word  = new Word();
//        Document doc = Jsoup.parse(html);
//        String[] splitString = html.split("<html>");
//        word.setWord_target(splitString[0]);
//        Elements title =  doc.select("i");
//        for (int i = 0;i<title.size();i++) {
//            if(i==0) {
//                String[] header = title.get(0).text().split(" ");
//                for (int j = 0; j < header.length; j++) {
////                   word.setWord_target(header[0]);
//                }
//            }
//            if (i>0){
//                //System.out.println(title.get(i).text());
//            }
//        }
//        Elements description = doc.select("ul");
//        ArrayList<Description> noTypeDes = new ArrayList<>();
//        ArrayList<Description> noExDes = new ArrayList<>();
//        for (int i = 0;i<description.size();i++) {
//            Elements elements = description.get(i).select("ul");
//            if (elements.size() > 1) {
//                Elements type = description.get(i).select("i");
//                if(type.size()>0) {
//                    String typeStr = type.get(0).text();
//                    ArrayList<Description> descriptionsArr = new ArrayList<>();
//                    for (int j = 1; j < elements.size(); j++) {
//                        Description des = new Description();
//                        Elements liEle =elements.get(j).select("li font");
//                        if (liEle.size()==1) {
//                            des.setDefinition(liEle.text());
//                            Elements miniElement = elements.get(j).select("ul");
//                            for (int k = 1; k < miniElement.size(); k++) {
//                                Elements moreMiniElement = miniElement.get(k).select("li");
//                                for (int l = 0; l < moreMiniElement.size(); l++) {
//                                    //System.out.println(moreMiniElement.get(l).text());
//                                    des.setExample(moreMiniElement.get(l).text());
//                                }
//                            }
//                        }
//                        else if (liEle.size()>1){
//                            // System.out.println("\n"+liEle.get(0).text());
//                            Elements miniElement = elements.get(j).select("ul");
//                            for (int k = 2; k < miniElement.size(); k++) {
//                                des.setDefinition(miniElement.get(k).select("li font").text());
//                                Elements moreMiniElement = miniElement.get(k).select("ul");
//                                for (int l = 1; l < moreMiniElement.size(); l++) {
//                                    Elements muchMoreMiniElement = moreMiniElement.get(l).select("li");
//                                    for (Element a: muchMoreMiniElement){
//                                        des.setExample(a.text());
//                                    }
//                                }
//                                k+=miniElement.size()-1;
//                            }
//                        }
//                        descriptionsArr.add(des);
//                    }
//                    word.addMeaning(typeStr,descriptionsArr);
//                    i += elements.size() - 1;
//                }
//                else {
//                    Description des = new Description();
//                    des.setDefinition(elements.get(0).select("li font").text());
//                    for (int j = 1; j < elements.size();j++){
//                        Elements miniElement = elements.get(j).select("li");
//                        des.setExample(miniElement.text());
//                    }
//                    noTypeDes.add(des);
//                    i+=elements.size()-1;
//                }
//
//            }
//            else {
//                Description des = new Description();
//                Elements miniElement = description.get(i).select("li");
//                for (int j = 0; j < miniElement.size(); j++){
//                    if (miniElement.size() == 1){
//                        des.setDefinition(miniElement.get(j).text());
//                    }
//                    else if (miniElement.size() > 1){
//                        System.out.println("Error here there are two or more definitions");
//                    }
//                }
//                noExDes.add(des);
//            }
//
//        }
//        if (noExDes.size() > 0){
//            word.addMeaning("noEx",noExDes);
//        }
//        if (noTypeDes.size() > 0){
//            word.addMeaning("noType",noTypeDes);
//        }
//        return  word;
//    }
//}
package Overall;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Extract {
    public  Word getWordSrc(String html){
        Word word  = new Word();
        Document doc = Jsoup.parse(html);
        String[] splitString = html.split("<html>");
        word.setWord_target(splitString[0]);
        Elements title =  doc.select("i");

        //Get Phonetics
        if (title.size()>0) {
            if (title.get(0).text().contains("/")) {
                String[] header = title.get(0).text().split("/");
                word.setPhonetic(header[1]);
            }
        }

        Elements description = doc.select("ul");
        ArrayList<Description> noTypeDes = new ArrayList<>();
        ArrayList<Description> noExDes = new ArrayList<>();
        for (int i = 0;i<description.size();i++) {
            Elements elements = description.get(i).select("ul");
            if (elements.size() > 1) {
                Elements type = description.get(i).select("i");
                if(type.size()>0) {
                    String typeStr = type.get(0).text();
                    ArrayList<Description> descriptionsArr = new ArrayList<>();
                    for (int j = 1; j < elements.size(); j++) {
                        Description des = new Description();
                        Elements liEle =elements.get(j).select("li font");
                        if (liEle.size()==1) {
                            des.setDefinition(liEle.text());
                            Elements miniElement = elements.get(j).select("ul");
                            for (int k = 1; k < miniElement.size(); k++) {
                                Elements moreMiniElement = miniElement.get(k).select("li");
                                for (int l = 0; l < moreMiniElement.size(); l++) {
                                    //System.out.println(moreMiniElement.get(l).text());
                                    des.setExample(moreMiniElement.get(l).text());
                                }
                            }
                        }
                        else if (liEle.size()>1){
                            // System.out.println("\n"+liEle.get(0).text());
                            Elements miniElement = elements.get(j).select("ul");
                            for (int k = 2; k < miniElement.size(); k++) {
                                des.setDefinition(miniElement.get(k).select("li font").text());
                                Elements moreMiniElement = miniElement.get(k).select("ul");
                                for (int l = 1; l < moreMiniElement.size(); l++) {
                                    Elements muchMoreMiniElement = moreMiniElement.get(l).select("li");
                                    for (Element a: muchMoreMiniElement){
                                        des.setExample(a.text());
                                    }
                                }
                                k+=miniElement.size()-1;
                            }
                        }
                        descriptionsArr.add(des);
                    }
                    word.addMeaning(typeStr,descriptionsArr);
                    i += elements.size() - 1;
                }
                else {
                    Description des = new Description();
                    des.setDefinition(elements.get(0).select("li font").text());
                    for (int j = 1; j < elements.size();j++){
                        Elements miniElement = elements.get(j).select("li");
                        des.setExample(miniElement.text());
                    }
                    noTypeDes.add(des);
                    i+=elements.size()-1;
                }

            }
            else {
                Description des = new Description();
                Elements miniElement = description.get(i).select("li");
                for (int j = 0; j < miniElement.size(); j++){
                    if (miniElement.size() == 1){
                        des.setDefinition(miniElement.get(j).text());
                    }
                    else if (miniElement.size() > 1){
                        System.out.println("Error here there are two or more definitions");
                    }
                }
                noExDes.add(des);
            }

        }
        if (noExDes.size() > 0){
            word.addMeaning("noEx",noExDes);
        }
        if (noTypeDes.size() > 0){
            word.addMeaning("noType",noTypeDes);
        }
        return  word;
    }
}