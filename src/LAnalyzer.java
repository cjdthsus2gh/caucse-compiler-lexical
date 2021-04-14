import org.json.simple.JSONObject;
import java.io.*;

public class LAnalyzer {

    public static void main(String[] args) {
        String inputText;
        String token = "";
        char ch_input;
        boolean end_check = true;
        int stance = 0;
        String prev_token_name = "";
        JSONObject tok_table = null;
        DFA[] dfa = new DFA[23];

        FileInput fileParse = new FileInput();
        ParseTable pt = new ParseTable();

        for (int i = 0; i < 23; i++) {
            tok_table = pt.splitTable(i);
            DFA temp = new DFA(tok_table);
            dfa[i] = temp;
            dfa[i].initDFA();
        }
        for (String inputFile : args) {
            inputText = fileParse.parseFile("./" + inputFile);
            System.out.println("Analyze" + inputFile);
            System.out.println("Input : " + inputText);
            for (int i = 0; i < inputText.length(); i++) {
                ch_input = inputText.charAt(i);
                end_check = false;
                for (int j = 0; j < 23; j++) {
                    if (ch_input == '-'&& j == 10 && ("IDENTIFIER".equals(prev_token_name)||"INTEGER".equals(prev_token_name))) {
                        dfa[10].setStance(2);
                        continue;
                    }
                    stance = dfa[j].getStance();
                    if (stance == 2) {
                        dfa[j].setStance(3);
                    }
                    else if (stance == 3) {
                        continue;
                    }
                    else if (stance == 1) {
                        end_check = (end_check|dfa[j].moveNextState(Character.toString(ch_input)));
                    }
                }
                if (end_check) {
                    token += ch_input;
                    continue;
                }
                for (int j = 0; j < 23; j++) {
                    if (dfa[j].getStance() == 2 &&
                    dfa[j].moveNextState("fin") == true) {
                        prev_token_name = dfa[j].getName();
                        if (dfa[j].getName().equals("WHITESPACES"))
                            break;
                        System.out.println("<" + dfa[j].getName() + "," + token + ">");
                        break;
                    }
                    if (j == 22) {
                        System.out.println("<Undefined," + ch_input + ">");
                        prev_token_name = "";
                        i += 1;
                    }
                }
                for (int j = 0; j < 23; j++) {
                    dfa[j].setStance(1);
                    dfa[j].initDFA();
                }
                token = "";
                i -= 1;
            }
        }
    }
}
    
