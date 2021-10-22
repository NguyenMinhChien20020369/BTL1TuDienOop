package Overall;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFileWithBufferedReader {
    public ArrayList<Word> read() {
        return this.ReadJsonFile("C:\\GitHub\\BTL1TuDienOop\\src\\main\\resources\\Data\\lingoes\\emps.json");
    }

    public  ArrayList<Word> ReadJsonFile(String path) {
        String line = null;
        JSONDecoder JSD = new JSONDecoder();
       // String[] words;
        ArrayList<Word> result = new ArrayList<>();
        FileInputStream Path = null;
        BufferedReader br = null;
        InputStreamReader inputStreamReader = null;
        try {
            Path = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(Path, "UTF8");
             br = new BufferedReader(inputStreamReader);
             line = br.readLine();
            result = JSD.Decoder(line);
//            while ((line = br.readLine()) != null) {
//                String tempWord_target = "";
//                String tempWord_explain = "";
//                boolean takeExplain = false;
//                for (int i = 0; i < line.length(); i++) {
//                    if (takeExplain) {
//                        tempWord_explain = tempWord_explain.concat(Character.toString(line.charAt(i)));
//                        continue;
//                    }
//                    if (line.charAt(i) != '\t') {
//                        tempWord_target = tempWord_target.concat(Character.toString(line.charAt(i)));
//                    } else {
//                        takeExplain = true;
//                    }
//                }
//                result.add(new Word(tempWord_target,tempWord_explain));
//            }
        }   catch (FileNotFoundException ex) {
                Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                        .log(Level.SEVERE, null, ex);
            } finally {
                try {
                    Path.close();
                    br.close();

                } catch (IOException ex) {
                    Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        return result;
    }

    public static ArrayList<Word> read(String path) {
        String line = null;
        JSONDecoder JSD = new JSONDecoder();
        // String[] words;
        ArrayList<Word> result = new ArrayList<>();
        FileInputStream Path = null;
        BufferedReader br = null;
        InputStreamReader inputStreamReader = null;
        try {
            Path = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(Path, "UTF8");
            br = new BufferedReader(inputStreamReader);
            line = br.readLine();
            result = JSD.Decoder(line);
            while ((line = br.readLine()) != null) {
                String tempWord_target = "";
                String tempWord_explain = "";
                boolean takeExplain = false;
                for (int i = 0; i < line.length(); i++) {
                    if (takeExplain) {
                        tempWord_explain = tempWord_explain.concat(Character.toString(line.charAt(i)));
                        continue;
                    }
                    if (line.charAt(i) != '\t') {
                        tempWord_target = tempWord_target.concat(Character.toString(line.charAt(i)));
                    } else {
                        takeExplain = true;
                    }
                }
                result.add(new Word(tempWord_target,tempWord_explain));
            }
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                Path.close();
                br.close();

            } catch (IOException ex) {
                Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void write(ArrayList<Word> words) {
        this.write(words, "data/save_dictionaries.txt");
    }

    public void write(ArrayList<Word> words, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            for (Word word : words) {
                bufferedWriter.write(word.getWord_target() + "\t" + word.getWord_explain());
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


