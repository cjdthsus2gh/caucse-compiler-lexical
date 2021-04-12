import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Scanner;

public class LAnalyzer {
    public static void main(String[] args) {
        FileInput file = new FileInput();
        ParseDFA dfa = new ParseDFA();
        Scanner scan = new Scanner(System.in);

        String fileName= "src\\"+ scan.next();
        String inputText= file.parseFile(fileName);

        dfa.initJSON();

        System.out.println(dfa.getT());

    }
}
    
