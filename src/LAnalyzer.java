import java.io.*;
import java.util.Scanner;

public class LAnalyzer {
    public static final int NUM_OF_TABLE = 23;

    public static void main(String[] args) {

        ParseTable pt = new ParseTable();
        DFA[] dfa = new DFA[NUM_OF_TABLE];

        FileInput file = new FileInput();
        Scanner scan = new Scanner(System.in);
        String fileName= "src/"+ scan.next();
        String inputText= file.parseFile(fileName) + " ";

        String input;
        String state = "T0";
        Boolean isFinish;

        String finalOutput, finalToken = null;

        int startPos = 0;
        int[] checkToken = new int[NUM_OF_TABLE+1];


        System.out.println(inputText);
        scan.next();

        for (int i = 0; i < NUM_OF_TABLE; i++) {
            dfa[i] = new DFA(pt.splitTable(i));
            dfa[i].resetDFA();
        }
        while(startPos < inputText.length()-1) {
            for(int T=0;T<NUM_OF_TABLE;T++){
                for(int i=startPos;i<inputText.length();i++) {

                    isFinish = dfa[T].getNextState("fin"); 
                    input = inputText.substring(i,i+1);
                   
                    if(input.equals("-") && T==10 &&       //"-"의 바로 앞 토큰이 Integer나 Identifier라면 "-"는 무조건 Operator로 취급.
                        (finalToken.equals("IDENTIFIER") || finalToken.equals("INTEGER")))
                              break;

                    if(!dfa[T].getNextState(input)) {     //c_state에 차례대로 symbol을 삽입.
                        dfa[T].resetDFA();                    //해당 DFA가 끝났을때 (or 문장이 끝났을 때)
                        if(isFinish)                        //Token으로 성립했다면
                            checkToken[T] = i-startPos;  //Token의 길이 저장
                        else
                            checkToken[T] = -1;           //Token성립 안되면 -1(false)
                        break;
                    }
                }
                //System.out.println(T+"번째 순회끝");
                dfa[T].resetDFA();
            }
            int max = 0;
            int index = -1;
            for(int i=0;i<NUM_OF_TABLE;i++) {      //제일 길게 파싱을 성공한 DFA 추출
                if(max < checkToken[i]) {          //길이가 같다면 먼저 추출된 DFA(우선순위가 높은 DFA)가 추출됨.
                    max = checkToken[i];
                    index = i;
                }
            }
            if(index == -1) {                      //어떤 DFA도 추출되지 않았다면 오류
                System.out.println("Lexical Error Occured!!");
                break;
            }
            finalToken = dfa[index].getName();
            finalOutput = inputText.substring(startPos,startPos+max);
            if(!finalToken.equals("WHITESPACES"))
                System.out.println("< "+finalToken+","+finalOutput+" >");
            startPos += max;
        }
    }
}
