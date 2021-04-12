import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class LAnalyzer {
    
    public static void main(String[] args) {
        
        String inputText;
        FileInput fileParse = new FileInput();
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        
        inputText = fileParse.parseFile();
            System.out.println(obj.get("COMP"));

            JSONObject obj2 = (JSONObject)obj.get("COMP");
            System.out.println(obj2.get("T1"));

        
        System.out.println("끝");

    }
}
    
