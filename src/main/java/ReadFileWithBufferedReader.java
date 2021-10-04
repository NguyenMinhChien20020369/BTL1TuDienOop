import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
public class ReadFileWithBufferedReader {
    public ArrayList<Word> read() {
        return this.read("src/main/resources/Data/lingoes/E_V.txt");
    }

    public ArrayList<Word> read(String path) {
        String line = null;
        String[] words;
        ArrayList<Word> result = new ArrayList<>();
        FileInputStream Path = null;
        BufferedReader bufferedReader = null;
        try {
            Path = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(Path, "UTF8");
             bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                int SplitWordFromDic = line.indexOf("<html>");
                if (SplitWordFromDic > 0 && SplitWordFromDic < line.length()) {
                    String Target = line.substring(0, SplitWordFromDic);
                    String explain = line.substring(SplitWordFromDic);
                    Word word = new Word(Target, explain);
                    result.add(word);
                }
            }
        }
           catch (FileNotFoundException ex) {
                Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                        .log(Level.SEVERE, null, ex);
            } finally {
                try {
                    Path.close();
                    bufferedReader.close();

                } catch (IOException ex) {
                    Logger.getLogger(ReadFileWithBufferedReader.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        return result;
    }

    public void write(ArrayList<Word> words) throws IOException {
        this.write(words, "src/main/resources/Data/lingoes/E_V.txt");
    }

    public void write(ArrayList<Word> words, String path) throws IOException {
        FileWriter fw = new FileWriter(path,true);
        try {

            for (Word word : words) {
                fw.write(word.getWord_target() + word.getWord_explain());
                fw.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fw != null) {
                    fw.flush();
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();            }
        }
    }
}


