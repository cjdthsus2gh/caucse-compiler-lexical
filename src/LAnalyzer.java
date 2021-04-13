import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Scanner;

public class LAnalyzer {
    public static void main(String[] args) {
        String finalInput;

        FileInput file = new FileInput();
        ParseDFA dfa = new ParseDFA();
        Scanner scan = new Scanner(System.in);

        String fileName= "src\\"+ scan.next();
        String inputText= file.parseFile(fileName);

        dfa.initJSON();

        int startPos = 0;
        String input;
        String state = "T0";

        String kindOfToken = dfa.getToken(1);
        Boolean isFinish;

        for(int order=1;order<4;order++){
            for(int i=startPos;i<=inputText.length();i++) {
                if(i==inputText.length())               //문장의 끝에 도달하면 중지.
                    break;
                isFinish = Boolean.parseBoolean(dfa.getState(kindOfToken, state, "fin"));
                input = Character.toString(inputText.charAt(i));  //input값이 whitespace일 경우 초기화.
                if(input == null) {
                    if(isFinish) {
                        state = "T0";
                        startPos = i+1;
                        break;
                    }
                    else {
                        state = "T0";
                        order++;
                    }
                }
                state = dfa.getState(kindOfToken, state, input);
                System.out.println(input + ", " + state + ", " + i);
                if(state == null && isFinish) {
                    System.out.println("순회끝");
                    state = "T0";
                }

            }
        }
        System.out.println("끝");
    }
}
    
