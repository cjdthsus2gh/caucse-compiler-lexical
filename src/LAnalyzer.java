import org.json.simple.JSONObject;
import java.io.*;

public class LAnalyzer {
    
    public static void main(String[] args) {
        String inputText;
        JSONObject tok_table = null;
        DFA[] dfa = new DFA[22];

        FileInput fileParse = new FileInput();
        ParseTable pt = new ParseTable();

        inputText = fileParse.parseFile("./input.txt");
        
        System.out.println(inputText);

        for (int i = 0; i < 22; i++) {
            tok_table = pt.splitTable(i);
            DFA temp = new DFA(tok_table);
            dfa[i] = temp;
        }
    }
}
    
