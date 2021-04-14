import org.json.simple.JSONObject;
import java.io.*;

public class LAnalyzer {

    public static void main(String[] args) {
        String inputText;
        char ch_input;
        boolean end_check = true;
        int stance = 0;
        JSONObject tok_table = null;
        DFA[] dfa = new DFA[23];

        FileInput fileParse = new FileInput();
        ParseTable pt = new ParseTable();

        inputText = fileParse.parseFile("./input.txt") + "  ";
        
        System.out.println(inputText);

        for (int i = 0; i < 23; i++) {
            tok_table = pt.splitTable(i);
            DFA temp = new DFA(tok_table);
            dfa[i] = temp;
            dfa[i].initDFA();
        }
        for (int i = 0; i < inputText.length(); i++) {
            ch_input = inputText.charAt(i);
            end_check = false;
            /*System.out.println("Input value is "+inputText);*/
            for (int j = 0; j < 23; j++) {
                /*System.out.println(dfa[j].getName());
                System.out.println("stance : " + dfa[j].getStance());*/
                stance = dfa[j].getStance();
                if (stance == 2) {
                    dfa[j].setStance(3);
                    /*System.out.println("moves to stance " + dfa[j].getStance());*/
                }
                else if (stance == 3) {
                    continue;
                }
                else if (stance == 1) {
                    end_check = (end_check|dfa[j].moveNextState(Character.toString(ch_input)));
                }
            }
            /*System.out.println(end_check);*/
            if (end_check)
                continue;
            for (int j = 0; j < 23; j++) {
                /*System.out.println(dfa[j].getName());
                System.out.println(dfa[j].getStance());
                System.out.println(dfa[j].getC_sate());*/
                if (dfa[j].getStance() == 2 &&
                dfa[j].moveNextState("fin") == true) {
                    System.out.println("<" + dfa[j].getName() + ">");
                }
                dfa[j].setStance(1);
                dfa[j].initDFA();
            }
            i -= 1;
        }
    }
}
    
