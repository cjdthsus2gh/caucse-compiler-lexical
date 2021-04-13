import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Scanner;

public class LAnalyzer {
    public static final int NUM_OF_TABLE = 22;

    public static void main(String[] args) {
        String finalInput;

        FileInput file = new FileInput();
        ParseDFA dfa = new ParseDFA();
        Scanner scan = new Scanner(System.in);

        String fileName= "src/"+ scan.next();
        String inputText= file.parseFile(fileName);
        inputText = inputText + " ";

        dfa.initJSON();

        String input;
        String state = "T0";
        Boolean isFinish;

        int startPos = 0;
        int[] checkToken = new int[NUM_OF_TABLE+1];

        while(true) {
            for(int Torder=1 ; Torder<=NUM_OF_TABLE ; Torder++){
                for(int i=startPos;i<inputText.length();i++) {

                    isFinish = Boolean.parseBoolean(dfa.getState(Torder, state, "fin")); 
                    input = inputText.substring(i,i+1);      
                    state = dfa.getState(Torder, state, input);

                    System.out.println(input + ", " + state + ", " + i);

                    if(state==null) { //해당 DFA가 끝났을때 (or 문장이 끝났을 때)
                        state = "T0";
                        if(isFinish) {                        //Token으로 성립했다면
                            System.out.println("checkToken의 값 : "+ (i-startPos));
                            checkToken[Torder] = i-startPos;  //Token의 길이 저장
                            System.out.println(dfa.getName(Torder));
                        }
                        else {
                            System.out.println("뭔가 이상함");
                            checkToken[Torder] = 0;           //Token성립 안되면 0(false)
                        }
                        break;
                    }
                }
                System.out.println(Torder+"번째 순회끝");
                state = "T0";
            }
            break;   
        }
        for(int i=1;i<=NUM_OF_TABLE;i++)
            System.out.println(checkToken[i]);
    }
}
